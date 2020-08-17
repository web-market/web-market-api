package market.service;

import market.dto.category.CategoryDto;
import market.entity.Category;
import market.projection.category.CategoryDropdownView;
import market.projection.category.CategoryEditView;
import market.projection.category.CategoryItemView;

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
