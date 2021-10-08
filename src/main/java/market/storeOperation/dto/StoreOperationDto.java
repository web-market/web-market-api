package market.storeOperation.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.entity.StoreOperationType;
import market.product.dto.ProductSupplyDto;
import market.store.dto.StoreSupplyDto;

@Data
@NoArgsConstructor
public class StoreOperationDto {

    private StoreSupplyDto fromStore;

    private StoreSupplyDto toStore;

    private ProductSupplyDto product;

    private Long quantity;

    private StoreOperationType operation;

}
