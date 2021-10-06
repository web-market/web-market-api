package market.storeProductSupplyAudit.dto;

import market.product.dto.ProductSupplyAudit;
import market.store.dto.StoreView;

public interface StoreProductSupplyAuditItemView {

    ProductSupplyAudit getProduct();

    StoreView getStore();

    Long getProductQuantity();

}
