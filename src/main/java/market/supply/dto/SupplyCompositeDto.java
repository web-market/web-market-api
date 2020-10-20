package market.supply.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.provider.dto.ProviderSupplyDto;
import market.store.dto.StoreSupplyDto;
import market.supplyRawProductAudit.dto.SupplyProductAuditDto;

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

    @NotNull(groups = {Create.class, Update.class})
    private String identificationNumber;

    @Valid
    private ProviderSupplyDto provider;

    @Valid
    private StoreSupplyDto store;

    @Valid
    private List<SupplyProductAuditDto> supplyProductAudit;

}
