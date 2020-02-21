package market.service;

import market.dto.category.CategoryDto;
import market.entity.Category;
import market.projection.category.CategoryDropdownView;
import market.projection.category.CategoryEditView;
import market.projection.category.CategoryItemView;

import java.util.List;

public interface CategoryService {

    List<CategoryDropdownView> getAll();

    CategoryEditView getById(Long id);

    Category create(CategoryDto categoryDto);

    Category update(CategoryDto categoryDto);

    List<CategoryItemView> getAllByParentCategoryId(Long id);

    void delete(Long id);

    List<CategoryDropdownView> getAvailableParentCategories(Long id);

}
