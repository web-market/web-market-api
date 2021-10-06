package market.supply;

import market.entity.Supply;
import market.entity.SupplyStatus;
import market.supply.dto.SupplyCompositeItemView;
import market.supply.dto.SupplyItemView;
import market.supply.dto.SupplySeparationInfoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SupplyRepository extends JpaRepository<Supply, Long> {

    //Projections
    List<SupplyItemView> findAllBy();

    List<SupplyItemView> findAllByStatusAndArrivalDate(SupplyStatus status, LocalDateTime date);

    SupplyItemView getById(Long id);

    SupplyCompositeItemView getSupplyCompositeItemViewById(Long id);

    SupplySeparationInfoView getSupplySeparationInfoViewById(Long id);

    //DTOs
    Boolean existsByIdentificationNumber(String identificationNUmber);

    Supply getSupplyById(Long id);

    @Query(value = "SELECT status FROM supply WHERE id = ?1", nativeQuery = true)
    SupplyStatus findStatusById(Long id);

    void deleteById(Long id);

}
