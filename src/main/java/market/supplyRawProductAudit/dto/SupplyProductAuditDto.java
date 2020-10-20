package market.supplyRawProductAudit.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.product.dto.ProductSupplyDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SupplyProductAuditDto {

    @Valid
    private ProductSupplyDto product;

    @NotNull(groups = {Create.class, Update.class})
    private Long productQuantity;

    @NotNull(groups = {Create.class, Update.class})
    private Double pricePerItem;

}
