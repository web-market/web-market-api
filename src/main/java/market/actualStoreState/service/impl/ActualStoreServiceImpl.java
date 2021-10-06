package market.actualStoreState.service.impl;

import lombok.RequiredArgsConstructor;
import market.actualStoreState.ActualStoreStateRepository;
import market.actualStoreState.dto.ActualStoreStateItemView;
import market.actualStoreState.service.ActualStoreService;
import market.entity.ActualStoreState;
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
    public List<ActualStoreStateItemView> getAllStores() {
        return this.actualStoreStateRepository.findAllBy();
    }
}
