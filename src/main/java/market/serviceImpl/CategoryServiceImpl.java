package market.serviceImpl;

import lombok.RequiredArgsConstructor;
import market.dto.category.CategoryDto;
import market.dto.category.CategoryDropdownDto;
import market.dto.category.CreateCategoryDto;
import market.entity.Category;
import market.repository.CategoryRepository;
import market.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<CategoryDto> getAllByParentCategoryId(Long id) {
        return checkForChildren(this.categoryRepository.getAllByParentCategoryId(id));
    }

    @Override
    public List<CategoryDropdownDto> getAll() {
        return this.modelMapper.map(this.categoryRepository.findAll(),
                new TypeToken<List<CategoryDropdownDto>>(){}.getType());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Category create(CreateCategoryDto createCategoryDto) {
        Category newCategory = this.modelMapper.map(createCategoryDto, Category.class);
        if (createCategoryDto.getParentCategoryId() != null) {
            Category parentCategory = this.categoryRepository.getById(createCategoryDto.getParentCategoryId());
            newCategory.setParentCategory(parentCategory);
        }
        return this.categoryRepository.save(newCategory);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Category update(CategoryDto categoryDto) {
        return this.categoryRepository.save(modelMapper.map(categoryDto, Category.class));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteCategory(Long id) {
        this.categoryRepository.deleteById(id);
    }

    private List<CategoryDto> checkForChildren(List<Category> categories) {
        List<CategoryDto> categoryDtos = new ArrayList<>();
        categories.forEach(category -> {
            CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
            if (category.getSubCategories().isEmpty()) {
                categoryDto.setHasSubCategories(false);
            } else {
                categoryDto.setHasSubCategories(true);
            }
            categoryDtos.add(categoryDto);
        });
        return categoryDtos;
    }

}
