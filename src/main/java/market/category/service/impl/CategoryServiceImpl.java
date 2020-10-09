package market.category.service.impl;

import lombok.RequiredArgsConstructor;
import market.category.dto.CategoryDto;
import market.entity.Category;
import market.category.dto.CategoryDropdownView;
import market.category.dto.CategoryEditView;
import market.category.dto.CategoryItemView;
import market.category.CategoryRepository;
import market.category.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDropdownView> getCategories() {
        return this.categoryRepository.findAllBy();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryItemView> getFirstLevelCategories() {
        return this.categoryRepository.findAllByParentCategoryIdIsNull();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryItemView> getAllByParent(Long id) {
        return this.categoryRepository.findAllByParentCategoryIdOrderBySortOrderDesc(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDropdownView> getAllowedParents(Long id) {
        List<Long> childIdsWithCurrentId = this.getChildCategoriesIds(id);
        childIdsWithCurrentId.add(id);
        return this.categoryRepository.findAllByIdIsNotIn(childIdsWithCurrentId);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryEditView getCategory(Long id) {
        return Optional.ofNullable(this.categoryRepository.findCategoryEditViewById(id))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getMentionedCategories(List<Long> ids) {
        return this.categoryRepository.getAllByIdIn(ids);
    }

    @Override
    @Transactional
    public Category create(CategoryDto createCategoryDto) {
        Category newCategory = this.modelMapper.map(createCategoryDto, Category.class);
        newCategory.setParentCategory(this.categoryRepository.getById(createCategoryDto.getParentCategoryId()));
        return this.categoryRepository.save(newCategory);
    }

    @Override
    @Transactional
    public Category update(CategoryDto updateCategoryDto) {
        Category category = this.modelMapper.map(updateCategoryDto, Category.class);
        category.setParentCategory(this.categoryRepository.getById(updateCategoryDto.getParentCategoryId()));
        return this.categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void delete(Long id, Boolean deleteChildren) {
        if (deleteChildren) {
            List<Long> categoriesToDelete = this.getChildCategoriesIds(id);
            categoriesToDelete.add(id);
            this.categoryRepository.deleteAllByIdIn(categoriesToDelete);
            return;
        }
        this.setAnotherParent(id);
        this.categoryRepository.deleteById(id);
    }

    private void setAnotherParent(Long id) {
        Category parentCategory = this.categoryRepository.getBySubCategoriesId(id);
        List<Category> childCategories = this.categoryRepository.getCategoriesByParentCategoryId(id)
                .stream()
                .peek(childCategory -> childCategory.setParentCategory(parentCategory))
                .collect(Collectors.toList());

        this.categoryRepository.saveAll(childCategories);
    }

    private List<Long> getChildCategoriesIds(Long id) {
        List<Long> childIds = new ArrayList<>();
        this.categoryRepository.findAllByParentCategoryId(id)
                .forEach(child -> {
                    childIds.add(child.getId());
                    childIds.addAll(getChildCategoriesIds(child.getId()));
                });
        return childIds;
    }
}
