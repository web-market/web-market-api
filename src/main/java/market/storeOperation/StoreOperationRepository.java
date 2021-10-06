package market.storeOperation;

import market.entity.StoreOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreOperationRepository extends JpaRepository<StoreOperation, Long> {

}
