package market.store.service.impl;

import lombok.RequiredArgsConstructor;
import market.store.dto.StoreDto;
import market.entity.Store;
import market.store.dto.StoreView;
import market.store.StoreRepository;
import market.store.service.StoreService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<StoreView> getStores() {
        return this.storeRepository.findAllBy();
    }

    @Override
    @Transactional(readOnly = true)
    public StoreView getStore(Long id) {
        return this.storeRepository.findOneById(id);
    }

    @Override
    @Transactional
    public Store create(StoreDto storeDto) {
        Store store = this.modelMapper.map(storeDto, Store.class);
        return this.storeRepository.save(store);
    }

    @Override
    @Transactional
    public Store update(StoreDto storeDto) {
        Store store = this.modelMapper.map(storeDto, Store.class);
        return this.storeRepository.save(store);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.storeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void bulkDelete(List<Long> ids) {
        this.storeRepository.deleteAllByIdIn(ids);
    }
}
