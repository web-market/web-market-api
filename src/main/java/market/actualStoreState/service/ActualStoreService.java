package market.actualStoreState.service;

import market.actualStoreState.dto.ActualStoreStateItemView;
import market.entity.ActualStoreState;
import market.supply.dto.ProductStoreProcessDto;

import java.util.List;

public interface ActualStoreService {

    List<ActualStoreState> persistData(List<ProductStoreProcessDto> productStoreProcessDtos);

    void shiftProducts(Long storeFrom, Long storeTo, Long productId, Long quantity);

    void removeProducts(Long storeFrom, Long productId, Long quantity);

    List<ActualStoreStateItemView> getAllStores();

    ActualStoreState getByStoreIdAndProductId(Long storeId, Long productId);

    boolean existsByStoreIdAndProductId(Long storeId, Long productId);

    boolean isEnoughProducts(Long productId, Long storeId, Long quantity);
}
