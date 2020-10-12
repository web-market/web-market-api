package market.product.productVariant;

import market.entity.ProductVariant;
import market.product.productVariant.dto.ProductVariantView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

    //Projections
    List<ProductVariantView> findAllBy();

    List<ProductVariantView> findAllByFilterValuesIdIn(List<Long> ids);

    ProductVariantView findOneById(Long id);

    //DTOs
    void deleteAllByIdIn(List<Long> ids);
}