package market.service;

import market.projection.supply.SupplyItemView;

import java.util.List;

public interface SupplyService {

    //Projections
    List<SupplyItemView> getSupplies();

    SupplyItemView getSupply(Long id);



}
