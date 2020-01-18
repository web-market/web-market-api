package market.repository;

import market.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category getById(Long id);

    List<Category> getAllByParentCategoryId(Long id);

}
