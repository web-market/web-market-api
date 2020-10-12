package market.product.rawProduct;

import market.entity.RawProduct;
import market.product.rawProduct.dto.RawProductView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//TODO: refactor by adding use of projections here
public interface RawProductRepository extends JpaRepository<RawProduct, Long> {

    //Projections
    List<RawProductView> findAllBy();

    RawProductView findOneById(Long id);

    //DTOs
    void deleteAllByIdIn(List<Long> ids);
}
