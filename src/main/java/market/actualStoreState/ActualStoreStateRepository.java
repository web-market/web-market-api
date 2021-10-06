package market.actualStoreState;

import market.actualStoreState.dto.ActualStoreStateItemView;
import market.entity.ActualStoreState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActualStoreStateRepository extends JpaRepository<ActualStoreState, Long> {

    //Projections
    List<ActualStoreStateItemView> findAllBy();


    //DTOs
    boolean existsByStoreIdAndProductId(Long storeId, Long productId);

    ActualStoreState getByStoreIdAndProductId(Long storeId, Long productId);

}
