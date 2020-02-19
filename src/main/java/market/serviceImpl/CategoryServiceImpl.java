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

import java.util.List;

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

    @Transactional(readOnly = true)
    public List<CategoryItemView> getAllByParentCategoryId(Long id) {
        return this.categoryRepository.getAllByParentCategoryId(id);
    }

    @Transactional(readOnly = true)
    public CategoryEditView getById(Long id) {
        return this.categoryRepository.getCategoryEditViewById(id);
    }

    @Transactional
    public Category create(CategoryDto createCategoryDto) {
        Category newCategory = this.modelMapper.map(createCategoryDto, Category.class);
        if (newCategory.getSortOrder() == null) {
            newCategory.setSortOrder(0L);
        }
        if (createCategoryDto.getParentCategoryId() != null) {
            Category parentCategory = this.categoryRepository.getById(createCategoryDto.getParentCategoryId());
            newCategory.setParentCategory(parentCategory);
        }
        return this.categoryRepository.save(newCategory);
    }

    @Transactional
    public Category update(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        category.setParentCategory(this.categoryRepository.getById(categoryDto.getParentCategoryId()));
        return this.categoryRepository.save(category);
    }

    @Transactional
    public void delete(Long id) {
        this.categoryRepository.deleteById(id);
    }

}
