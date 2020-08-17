package market.repository;

import market.entity.Category;
import market.projection.category.CategoryDropdownView;
import market.projection.category.CategoryEditView;
import market.projection.category.CategoryItemView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    //Projections
    List<CategoryDropdownView> findAllBy();

    List<CategoryDropdownView> findAllByIdIsNotIn(List<Long> id);

    List<CategoryDropdownView> getAllByParentCategoryId(Long id);

    List<CategoryItemView> getAllByParentCategoryIdIsNull();

    List<CategoryItemView> getAllByParentCategoryIdOrderBySortOrderDesc(Long id);

    CategoryEditView getCategoryEditViewById(Long id);

    //DTOs
    Category getById(Long id);

    Category getBySubCategoriesId(Long id);

    List<Category> getAllByIdIn(List<Long> id);

    List<Category> getCategoriesByParentCategoryId(Long id);

    void deleteAllByIdIn(List<Long> ids);

}
