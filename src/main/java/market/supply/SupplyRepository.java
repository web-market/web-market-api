package market.supply;

import market.entity.Supply;
import market.supply.dto.SupplyCompositeItemView;
import market.supply.dto.SupplyItemView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplyRepository extends JpaRepository<Supply, Long> {

    //Projections
    List<SupplyItemView> findAllBy();

    SupplyItemView getById(Long id);

    SupplyCompositeItemView getSupplyCompositeItemViewById(Long id);

    //DTOs
    Boolean existsByIdentificationNumber(String identificationNUmber);

}
