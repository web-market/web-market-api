package market.serviceImpl;

import lombok.RequiredArgsConstructor;
import market.dto.category.CategoryDto;
import market.entity.Category;
import market.projection.category.CategoryDropdownView;
import market.projection.category.CategoryEditView;
import market.projection.category.CategoryItemView;
import market.repository.CategoryRepository;
import market.service.CategoryService;
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
    public List<CategoryDropdownView> getAll() {
        return this.categoryRepository.findAllBy();
    }

    @Override
    public List<Category> getByIdIn(List<Long> ids) {
        return this.categoryRepository.getAllByIdIn(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryItemView> getAllByParentCategoryId(Long id) {
        return this.categoryRepository.getAllByParentCategoryIdOrderBySortOrderDesc(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryItemView> getAllRootCategories() {
        return this.categoryRepository.getAllByParentCategoryIdIsNull();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryEditView getById(Long id) {
        return Optional.ofNullable(this.categoryRepository.getCategoryEditViewById(id))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDropdownView> getAvailableParentCategories(Long id) {
        List<Long> childIdsWithCurrentId = this.getChildCategoriesIds(id);
        childIdsWithCurrentId.add(id);
        return this.categoryRepository.findAllByIdIsNotIn(childIdsWithCurrentId);
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
        Category category = modelMapper.map(updateCategoryDto, Category.class);
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
        this.categoryRepository.getAllByParentCategoryId(id)
                .forEach(child -> {
                    childIds.add(child.getId());
                    childIds.addAll(getChildCategoriesIds(child.getId()));
                });
        return childIds;
    }
}
