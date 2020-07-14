package market.service.impl;

import lombok.RequiredArgsConstructor;
import market.dto.store.StoreDto;
import market.entity.Store;
import market.projection.store.StoreView;
import market.repository.StoreRepository;
import market.service.StoreService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StoreView> getAll() {
        return this.storeRepository.findAllBy();
    }

    @Override
    public StoreView getById(Long id) {
        return this.storeRepository.getById(id);
    }

    @Override
    public Store create(StoreDto storeDto) {
        Store store = this.modelMapper.map(storeDto, Store.class);
        return this.storeRepository.save(store);
    }

    @Override
    public Store update(StoreDto storeDto) {
        Store store = this.modelMapper.map(storeDto, Store.class);
        return this.storeRepository.save(store);
    }

    @Override
    public void delete(Long id) {
        this.storeRepository.deleteById(id);
    }

    @Override
    public void bulkDelete(List<Long> ids) {
        this.storeRepository.deleteAllByIdIn(ids);
    }
}
