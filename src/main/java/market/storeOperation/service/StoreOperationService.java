package market.storeOperation.service;

import market.storeOperation.dto.StoreOperationDto;

public interface StoreOperationService {

    void performOperation(StoreOperationDto shiftOperationDto);

}
