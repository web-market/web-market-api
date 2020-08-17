package market.repository;

import market.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

    List<ProductVariant> getAllByFilterValuesIdIn(List<Long> ids);

    ProductVariant findOneById(Long id);

    //DTOs
    void deleteAllByIdIn(List<Long> ids);
}