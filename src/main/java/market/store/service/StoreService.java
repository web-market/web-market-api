package market.store.service;

import market.store.dto.StoreDto;
import market.entity.Store;
import market.store.dto.StoreView;

import java.util.List;

public interface StoreService {

    //Projections
    List<StoreView> getStores();

    StoreView getStore(Long id);

    //DTOs
    Store create(StoreDto storeDto);

    Store update(StoreDto storeDto);

    void delete(Long id);

    void bulkDelete(List<Long> ids);

}
