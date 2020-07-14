package market.service;

import market.dto.store.StoreDto;
import market.entity.Store;
import market.projection.store.StoreView;

import java.util.List;

public interface StoreService {

    List<StoreView> getAll();

    StoreView getById(Long id);

    Store create(StoreDto storeDto);

    Store update(StoreDto storeDto);

    void delete(Long id);

    void bulkDelete(List<Long> ids);

}
