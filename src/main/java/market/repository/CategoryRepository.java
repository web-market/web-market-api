package market.repository;

import market.entity.Category;
import market.projection.category.CategoryDropdownView;
import market.projection.category.CategoryEditView;
import market.projection.category.CategoryItemView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<CategoryDropdownView> findAllBy();

    List<CategoryItemView> getAllByParentCategoryId(Long id);

    CategoryEditView getCategoryEditViewById(Long id);

    Category getById(Long id);
}
