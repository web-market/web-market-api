package market.repository;

import market.entity.UIProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UIProductRepository extends JpaRepository<UIProduct, Long> {

    UIProduct findOneById(Long id);

    void deleteAllByIdIn(List<Long> ids);

//    List<UIProduct> getAllByCategoriesIdIn(List<Long> ids);

    List<UIProduct> getAllByNameContainingIgnoreCase(String name);
}