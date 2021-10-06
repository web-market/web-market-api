package market.actualStoreState.dto;

import market.product.dto.ProductView;
import market.store.dto.StoreView;

public interface ActualStoreStateItemView {

    Long getId();

    StoreView getStore();

    ProductView getProduct();

}
