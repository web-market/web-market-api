package market.product;

import market.entity.Product;
import market.product.dto.ProductView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //Projections
    List<ProductView> findAllBy();

    List<ProductView> findAllByFilterValuesIdIn(List<Long> ids);

    ProductView findOneById(Long id);

    //DTOs
    void deleteAllByIdIn(List<Long> ids);
}