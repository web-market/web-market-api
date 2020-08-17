package market.repository;

import market.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

    ProductVariant findOneById(Long id);

    void deleteAllByIdIn(List<Long> ids);

    List<ProductVariant> getAllByFilterValuesIdIn(List<Long> ids);
}