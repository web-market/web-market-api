package market.service;

import market.dto.category.CategoryDropdownDto;
import market.dto.category.CategoryDto;
import market.dto.category.CategorySideMenuDto;
import market.entity.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDropdownDto> getAll();

    List<CategoryDto> getAllFull();

    Category create(CategoryDto categoryDto);

    Category update(CategoryDto categoryDto);

    List<CategorySideMenuDto> getSideMenu();


    void deleteCategory(Long id);

}
