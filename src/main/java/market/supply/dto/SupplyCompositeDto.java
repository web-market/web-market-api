package market.supply.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.product.rawProduct.dto.SupplyRawProductAuditDto;
import market.provider.dto.ProviderDto;
import market.store.dto.StoreDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class SupplyCompositeDto {

    private LocalDateTime arrivalDate;

    private String comment;

    private ProviderDto provider;

    private StoreDto store;

    private List<SupplyRawProductAuditDto> supplyRawProductAudit;

}
