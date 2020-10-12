package market.product.uiProduct;

import market.entity.UIProduct;
import market.product.uiProduct.dto.UIProductView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UIProductRepository extends JpaRepository<UIProduct, Long> {

    //Projections
    List<UIProductView> findAllByNameContainingIgnoreCase(String name);

    List<UIProductView> findAllBy();

    //TODO: Stanislav should take a look to this one
    //List<UIProduct> getAllByCategoriesIdIn(List<Long> ids);

    UIProductView findOneById(Long id);

    //DTOs
    void deleteAllByIdIn(List<Long> ids);

}