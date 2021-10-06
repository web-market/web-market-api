package market.storeProductSupplyAudit.service.impl;

import lombok.RequiredArgsConstructor;
import market.actualStoreState.service.ActualStoreService;
import market.entity.ActualStoreState;
import market.entity.StoreProductSupplyAudit;
import market.entity.Supply;
import market.exceptions.WrongProductSeparationException;
import market.storeProductSupplyAudit.StoreProductSupplyAuditRepository;
import market.storeProductSupplyAudit.service.StoreProductSupplyAuditService;
import market.supply.dto.ProductStoreProcessDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreProductSupplyAuditServiceImpl implements StoreProductSupplyAuditService {

    private final StoreProductSupplyAuditRepository storeProductSupplyAuditRepository;
    private final ModelMapper modelMapper;
    private final ActualStoreService actualStoreService;

    @Override
    public List<ActualStoreState> create(List<ProductStoreProcessDto> productStoreProcessDtos, Supply supply) {
        List<StoreProductSupplyAudit> audits = this.modelMapper.map(productStoreProcessDtos,
                new TypeToken<List<StoreProductSupplyAudit>>() {}.getType());

        productStoreProcessDtos.forEach(productStoreProcessDto -> {
            boolean isValidPlacement = productPlacementCheck(productStoreProcessDto.getProduct().getId(), productStoreProcessDtos, supply);
            if (!isValidPlacement) {
                throw new WrongProductSeparationException("Quantity of products was separated wrongly between stores.");
            }
        });

        populateSupply(audits, supply);
        this.storeProductSupplyAuditRepository.saveAll(audits);

        //Saving or populating products for managing actual store state
        return this.actualStoreService.persistData(productStoreProcessDtos);
    }

    private void populateSupply(List<StoreProductSupplyAudit> audits, Supply supply) {
        audits.forEach(audit -> audit.setSupply(supply));
    }

    private boolean productPlacementCheck(Long productId, List<ProductStoreProcessDto> productStoreProcessDtos, Supply supply) {
        Long quantityInPlacement = productStoreProcessDtos
                .stream()
                .mapToLong(productStoreProcessDto -> productStoreProcessDto.getProduct().getId().equals(productId)
                        ? productStoreProcessDto.getProductQuantity() : 0L)
                .sum();
        Long supplyQuantity = supply.getSupplyProductAudits()
                .stream()
                .filter(audit -> audit.getProduct().getId().equals(productId))
                .findFirst().get().getProductQuantity();

        return quantityInPlacement.equals(supplyQuantity);
    }
}
