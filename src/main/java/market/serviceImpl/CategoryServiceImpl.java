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
        if (createCategoryDto.getParentCategoryId() != null) {
            Category parentCategory = this.categoryRepository.getById(createCategoryDto.getParentCategoryId());
            newCategory.setParentCategory(parentCategory);
        }
        return this.categoryRepository.save(newCategory);
    }

    @Override
    @Transactional
    public Category update(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        category.setParentCategory(this.categoryRepository.getById(categoryDto.getParentCategoryId()));
        return this.categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.categoryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(Long id, Boolean deleteChildren) {
        if (deleteChildren) {
            List<Long> categoryIdsToDelete = this.getChildCategoriesIds(id);
            categoryIdsToDelete.add(id);
            this.categoryRepository.deleteAllByIdIn(categoryIdsToDelete);
            return;
        }
        Category currentCategory = this.categoryRepository.getById(id);
        if (currentCategory.getParentCategory() != null) {
            Category newParent = currentCategory.getParentCategory();
            List<Category> childCategories = this.categoryRepository.getCategoriesByParentCategoryId(id);

            childCategories.forEach(childCategory -> {
                childCategory.setParentCategory(newParent);
                this.categoryRepository.save(childCategory);
            });
        }
        this.categoryRepository.delete(currentCategory);
    }

    private List<Long> getChildCategoriesIds(Long id) {
        List<Long> childIds = new ArrayList<>();
        this.categoryRepository.getAllByParentCategoryId(id).forEach(child -> {
            childIds.add(child.getId());
            childIds.addAll(getChildCategoriesIds(child.getId()));
        });
        return childIds;
    }
}
