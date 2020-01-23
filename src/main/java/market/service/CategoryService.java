package market.service;

import market.dto.category.CategoryDropdownDto;
import market.dto.category.CategoryDto;
import market.dto.category.CreateCategoryDto;
import market.entity.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDropdownDto> getAll();

    Category getById(Long id);

    Category create(CreateCategoryDto createCategoryDto);

    Category update(CategoryDto categoryDto);

    List<CategoryDto> getAllByParentCategoryId(Long id);

    void deleteCategory(Long id);

}
