package market.supply.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.product.dto.ProductSupplyDto;
import market.store.dto.StoreSupplyDto;

@Data
@NoArgsConstructor
public class ProductStoreProcessDto {

    private StoreSupplyDto store;

    private ProductSupplyDto product;

    private Long productQuantity;

}
