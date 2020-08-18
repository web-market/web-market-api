package market.repository;

import market.entity.Store;
import market.projection.store.StoreView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    //Projections
    List<StoreView> findAllBy();

    StoreView findOneById(Long id);

    //DTOs
    void deleteAllByIdIn(List<Long> ids);

}
