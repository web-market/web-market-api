package market.actualStoreState;

import market.actualStoreState.dto.ActualStoreStateItemView;
import market.entity.ActualStoreState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActualStoreStateRepository extends JpaRepository<ActualStoreState, Long> {

    //Projections
    List<ActualStoreStateItemView> findAllBy();


    //DTOs
    boolean existsByStoreIdAndProductId(Long storeId, Long productId);

    ActualStoreState getByStoreIdAndProductId(Long storeId, Long productId);

    @Query(value = "SELECT CASE WHEN actual_store_state.overall_quantity >= ?3 THEN true ELSE false END FROM actual_store_state WHERE product_id = ?1 AND store_id = ?2", nativeQuery = true)
    boolean isEnoughProducts(Long productId, Long storeId, Long quantity);

}
