package market.supplyRawProductAudit.dto;

import market.product.rawProduct.dto.RawProductForAuditItem;

public interface SupplyRawProductAuditItemView {

    RawProductForAuditItem getRawProduct();

    Long getRawProductQuantity();

    Double getPricePerItem();

}
