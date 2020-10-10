package market.product.rawProduct.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SupplyRawProductAuditDto {

    private RawProductForAuditDto rawProduct;

    private Long rawProductQuantity;

    private Double pricePerItem;

}
