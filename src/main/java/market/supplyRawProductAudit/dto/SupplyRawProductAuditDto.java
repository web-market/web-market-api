package market.supplyRawProductAudit.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.product.rawProduct.dto.RawProductForAuditDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SupplyRawProductAuditDto {

    @Valid
    private RawProductForAuditDto rawProduct;

    @NotNull(groups = {Create.class, Update.class})
    private Long rawProductQuantity;

    @NotNull(groups = {Create.class, Update.class})
    private Double pricePerItem;

}
