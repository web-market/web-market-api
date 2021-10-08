package market.actualStoreState.service.impl;

import lombok.RequiredArgsConstructor;
import market.actualStoreState.ActualStoreStateRepository;
import market.actualStoreState.dto.ActualStoreStateItemView;
import market.actualStoreState.service.ActualStoreService;
import market.entity.ActualStoreState;
import market.entity.Product;
import market.entity.Store;
import market.supply.dto.ProductStoreProcessDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActualStoreServiceImpl implements ActualStoreService {

    private final ActualStoreStateRepository actualStoreStateRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public List<ActualStoreState> persistData(List<ProductStoreProcessDto> productStoreProcessDtos) {
        return productStoreProcessDtos.stream().map(productStoreProcessDto -> {
            boolean exist = this.actualStoreStateRepository.existsByStoreIdAndProductId(
                    productStoreProcessDto.getStore().getId(), productStoreProcessDto.getProduct().getId());
            if (exist) {
                ActualStoreState actualStoreState = this.actualStoreStateRepository.getByStoreIdAndProductId(
                        productStoreProcessDto.getStore().getId(), productStoreProcessDto.getProduct().getId());
                Long currentQuantity = actualStoreState.getOverallQuantity();
                actualStoreState.setOverallQuantity(currentQuantity + productStoreProcessDto.getProductQuantity());
                return this.actualStoreStateRepository.save(actualStoreState);
            }
            ActualStoreState actualStoreState = this.modelMapper.map(productStoreProcessDto, ActualStoreState.class);
            actualStoreState.setOverallQuantity(productStoreProcessDto.getProductQuantity());
            return this.actualStoreStateRepository.save(actualStoreState);
        }).collect(Collectors.toList());
    }

    @Override
    public void shiftProducts(Long storeFrom, Long storeTo, Long productId, Long quantity) {
        ActualStoreState actualStoreFrom = this.actualStoreStateRepository.getByStoreIdAndProductId(storeFrom, productId);
        Long overallQuantityInStoreFrom = actualStoreFrom.getOverallQuantity();
        actualStoreFrom.setOverallQuantity(overallQuantityInStoreFrom - quantity);

        ActualStoreState actualStoreTo = this.actualStoreStateRepository.getByStoreIdAndProductId(storeTo, productId);
        if (actualStoreFrom.getId() != null) {
            Long overallQuantityInStoreTo = actualStoreTo.getOverallQuantity();
            actualStoreTo.setOverallQuantity(overallQuantityInStoreTo + quantity);
            this.actualStoreStateRepository.saveAll(List.of(actualStoreFrom, actualStoreTo));
            return;
        }

        final ActualStoreState newStoreForProduct = new ActualStoreState();
        Store store = new Store();
        store.setId(storeTo);
        Product product = new Product();
        product.setId(productId);

        newStoreForProduct.setStore(store);
        newStoreForProduct.setProduct(product);
        newStoreForProduct.setOverallQuantity(quantity);

        this.actualStoreStateRepository.saveAll(List.of(actualStoreFrom, newStoreForProduct));

    }

    @Override
    public void removeProducts(Long storeFrom, Long productId, Long quantity) {
        ActualStoreState actualStore = this.actualStoreStateRepository.getByStoreIdAndProductId(storeFrom, productId);
        Long overallQuantityInStore = actualStore.getOverallQuantity();
        actualStore.setOverallQuantity(overallQuantityInStore - quantity);

        this.actualStoreStateRepository.save(actualStore);
    }

    @Override
    public List<ActualStoreStateItemView> getAllStores() {
        return this.actualStoreStateRepository.findAllBy();
    }

    @Override
    public ActualStoreState getByStoreIdAndProductId(Long storeId, Long productId) {
        return this.actualStoreStateRepository.getByStoreIdAndProductId(storeId, productId);
    }

    @Override
    public boolean existsByStoreIdAndProductId(Long storeId, Long productId) {
        return this.actualStoreStateRepository.existsByStoreIdAndProductId(storeId, productId);
    }

    @Override
    public boolean isEnoughProducts(Long productId, Long storeId, Long quantity) {
        return this.actualStoreStateRepository.isEnoughProducts(productId, storeId, quantity);
    }
}
