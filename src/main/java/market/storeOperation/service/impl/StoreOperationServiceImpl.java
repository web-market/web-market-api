package market.storeOperation.service.impl;

import lombok.RequiredArgsConstructor;
import market.actualStoreState.service.ActualStoreService;
import market.entity.StoreOperation;
import market.entity.StoreOperationType;
import market.exceptions.NotEnoughProductsToPerformOperationException;
import market.storeOperation.StoreOperationRepository;
import market.storeOperation.dto.StoreOperationDto;
import market.storeOperation.service.StoreOperationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreOperationServiceImpl implements StoreOperationService {

    private final StoreOperationRepository storeOperationRepository;
    private final ActualStoreService actualStoreService;
    private final ModelMapper modelMapper;

    @Override
    public void performOperation(StoreOperationDto storeOperationDto) {
        switch (storeOperationDto.getOperation()) {
            case SHIFT:
                shiftProducts(storeOperationDto);
                break;
            case REMOVAL:
                removeProducts(storeOperationDto);
                break;
        }
    }

    private void removeProducts(StoreOperationDto storeOperationDto) {
        Long idFrom = storeOperationDto.getFromStore().getId();
        Long productId = storeOperationDto.getProduct().getId();
        Long quantity = storeOperationDto.getQuantity();

        boolean isEnoughProductsToRemove = this.actualStoreService.isEnoughProducts(productId, idFrom, quantity);

        if(!isEnoughProductsToRemove) {
            throw new NotEnoughProductsToPerformOperationException("");
        }

        this.actualStoreService.removeProducts(idFrom, productId, quantity);
        StoreOperation storeOperation = this.modelMapper.map(storeOperationDto, StoreOperation.class);
        storeOperation.setOperation(StoreOperationType.REMOVAL);
        this.storeOperationRepository.save(storeOperation);
    }

    private void shiftProducts(StoreOperationDto storeOperationDto) {
        final Long idFrom = storeOperationDto.getFromStore().getId();
        final Long idTo = storeOperationDto.getToStore().getId();
        final Long productId = storeOperationDto.getProduct().getId();
        final Long quantity = storeOperationDto.getQuantity();
        final boolean isEnoughProductsToShift = this.actualStoreService.isEnoughProducts(productId, idFrom, quantity);

        if (!isEnoughProductsToShift) {
            throw new NotEnoughProductsToPerformOperationException("");
        }

        this.actualStoreService.shiftProducts(idFrom, idTo, productId, quantity);
        StoreOperation storeOperation = this.modelMapper.map(storeOperationDto, StoreOperation.class);
        storeOperation.setOperation(StoreOperationType.SHIFT);
        this.storeOperationRepository.save(storeOperation);
    }

}
