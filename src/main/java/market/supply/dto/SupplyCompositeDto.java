package market.supply.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.supplyRawProductAudit.dto.SupplyRawProductAuditDto;
import market.provider.dto.ProviderDto;
import market.store.dto.StoreDto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class SupplyCompositeDto {

    @NotNull
    private LocalDateTime arrivalDate;

    private String comment;

    @NotNull
    private ProviderDto provider;

    @NotNull
    private StoreDto store;

    @NotNull
    private List<SupplyRawProductAuditDto> supplyRawProductAudit;

}
