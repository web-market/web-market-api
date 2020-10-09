package market.category.service;

import market.category.dto.CategoryDto;
import market.entity.Category;
import market.category.dto.CategoryDropdownView;
import market.category.dto.CategoryEditView;
import market.category.dto.CategoryItemView;

import java.util.List;

public interface CategoryService {

    //Projections
    List<CategoryDropdownView> getCategories();

    List<CategoryItemView> getFirstLevelCategories();

    List<CategoryItemView> getAllByParent(Long id);

    List<CategoryDropdownView> getAllowedParents(Long id);

    CategoryEditView getCategory(Long id);

    //DTOs
    //Not sure for what we need this method
    List<Category> getMentionedCategories(List<Long> ids);

    Category create(CategoryDto categoryDto);

    Category update(CategoryDto categoryDto);

    void delete(Long id, Boolean deleteChildren);
}
