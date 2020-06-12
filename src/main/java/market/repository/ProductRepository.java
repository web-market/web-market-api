package market.repository;

import market.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findOneById(Long id);

    void deleteAllByIdIn(List<Long> ids);

    List<Product> getAllByCategoriesIdIn(List<Long> ids);

    List<Product> getAllByFilterValuesIdIn(List<Long> ids);

    List<Product> getAllByNameContainingIgnoreCase(String name);
}