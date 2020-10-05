package market.projection.supply;

import market.projection.provider.ProviderItemView;
import market.projection.store.StoreView;

import java.time.LocalDateTime;

public interface SupplyItemView {

    Long getId();

    String getName();

    String getComment();

    LocalDateTime getArrivalDate();

    StoreView getStore();

    ProviderItemView getProvider();
}
