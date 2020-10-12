package market.supplyRawProductAudit.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.product.rawProduct.dto.RawProductForAuditDto;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SupplyRawProductAuditDto {

    @NotNull
    private RawProductForAuditDto rawProduct;

    @NotNull
    private Long rawProductQuantity;

    @NotNull
    private Double pricePerItem;

}
