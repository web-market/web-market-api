package market.supply.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SupplyProcessingDto {

    private Long id;

    private List<ProductStoreProcessDto> placementInStores;

}
