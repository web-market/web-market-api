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

    List<CategoryDropdownView> getAllByParentCategoryId(Long id);

    List<CategoryItemView> getAllByParentCategoryIdOrderBySortOrderDesc(Long id);

    List<CategoryItemView> getAllByParentCategoryIdIsNull();

    CategoryEditView getCategoryEditViewById(Long id);

    List<CategoryDropdownView> findAllByIdIsNotIn(List<Long> id);

    void deleteAllByIdIn(List<Long> ids);

    //For dtos but just check
    Category getById(Long id);

    Category getBySubCategoriesId(Long id);

    List<Category> getCategoriesByParentCategoryId(Long id);

}
