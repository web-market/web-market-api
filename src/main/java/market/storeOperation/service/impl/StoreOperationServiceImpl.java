package market.storeOperation.service.impl;

import lombok.RequiredArgsConstructor;
import market.storeOperation.StoreOperationRepository;
import market.storeOperation.service.StoreOperationService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreOperationServiceImpl implements StoreOperationService {

    private final StoreOperationRepository storeOperationRepository;

}
