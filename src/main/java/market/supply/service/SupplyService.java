package market.supply.service;

import market.entity.ActualStoreState;
import market.entity.StoreProductSupplyAudit;
import market.entity.Supply;
import market.supply.dto.*;

import java.time.LocalDateTime;
import java.util.List;

public interface SupplyService {

    //Projections
    List<SupplyItemView> getSupplies();

    List<SupplyItemView> getSuppliesByStatusAndArrivalDate(String status, LocalDateTime date);

    SupplyItemView getSupply(Long id);

    SupplyCompositeItemView getSupplyCompositeItemViewById(Long id);

    SupplySeparationInfoView getSupplySeparationInfo(Long id);

    //DTOs
    Supply create(SupplyCompositeDto supplyCompositeDto);

    Boolean isIdentificationNumberUnique(String identificationNumber);

    //Return something useful for user after supply processing
    List<ActualStoreState> processSupply(SupplyProcessingDto supplyProcessingDto);

    void delete(Long id);
}
