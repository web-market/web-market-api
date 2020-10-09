package market.supply.service;

import market.supply.dto.SupplyItemView;

import java.util.List;

public interface SupplyService {

    //Projections
    List<SupplyItemView> getSupplies();

    SupplyItemView getSupply(Long id);



}
