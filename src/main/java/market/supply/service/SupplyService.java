package market.supply.service;

import market.entity.Supply;
import market.supply.dto.SupplyCompositeDto;
import market.supply.dto.SupplyCompositeItemView;
import market.supply.dto.SupplyItemView;

import java.util.List;

public interface SupplyService {

    //Projections
    List<SupplyItemView> getSupplies();

    SupplyItemView getSupply(Long id);

    SupplyCompositeItemView getSupplyCompositeItemViewById(Long id);

    //DTOs
    Supply create(SupplyCompositeDto supplyCompositeDto);

    Boolean isIdentificationNumberUnique(String identificationNumber);

}
