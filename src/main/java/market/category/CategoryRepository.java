package market.category;

import market.entity.Category;
import market.category.dto.CategoryDropdownView;
import market.category.dto.CategoryEditView;
import market.category.dto.CategoryItemView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    //Projections
    List<CategoryDropdownView> findAllBy();

    List<CategoryDropdownView> findAllByIdIsNotIn(List<Long> id);

    List<CategoryDropdownView> findAllByParentCategoryId(Long id);

    List<CategoryItemView> findAllByParentCategoryIdIsNull();

    List<CategoryItemView> findAllByParentCategoryIdOrderBySortOrderDesc(Long id);

    CategoryEditView findCategoryEditViewById(Long id);

    //DTOs
    Category getById(Long id);

    Category getBySubCategoriesId(Long id);

    List<Category> getAllByIdIn(List<Long> id);

    List<Category> getCategoriesByParentCategoryId(Long id);

    void deleteAllByIdIn(List<Long> ids);

}
