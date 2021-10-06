package market.storeProductSupplyAudit.service;

import market.entity.ActualStoreState;
import market.entity.Supply;
import market.supply.dto.ProductStoreProcessDto;

import java.util.List;

public interface StoreProductSupplyAuditService {

    List<ActualStoreState> create(List<ProductStoreProcessDto> productStoreProcessDto, Supply supply);

}
