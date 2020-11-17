package market.supplyProductAudit.dto;

import market.product.dto.ProductSupplyAudit;

public interface SupplyProductAuditItemView {

    ProductSupplyAudit getProduct();

    Long getProductQuantity();

    Double getPricePerItem();

}
