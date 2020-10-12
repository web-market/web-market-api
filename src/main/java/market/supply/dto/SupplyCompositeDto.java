package market.supply.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.provider.dto.ProviderDto;
import market.store.dto.StoreDto;
import market.supplyRawProductAudit.dto.SupplyRawProductAuditDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class SupplyCompositeDto {

    @NotNull(groups = {Create.class, Update.class})
    private LocalDateTime arrivalDate;

    private String comment;

    @Valid
    private ProviderDto provider;

    @Valid
    private StoreDto store;

    @Valid
    private List<SupplyRawProductAuditDto> supplyRawProductAudit;

}
