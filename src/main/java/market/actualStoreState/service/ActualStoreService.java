package market.actualStoreState.service;

import market.actualStoreState.dto.ActualStoreStateItemView;
import market.entity.ActualStoreState;
import market.supply.dto.ProductStoreProcessDto;

import java.util.List;

public interface ActualStoreService {

    List<ActualStoreState> persistData(List<ProductStoreProcessDto> productStoreProcessDtos);

    List<ActualStoreStateItemView> getAllStores();
}
