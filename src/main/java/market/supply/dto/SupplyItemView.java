package market.supply.dto;

import market.provider.dto.ProviderItemView;
import market.store.dto.StoreView;

import java.time.LocalDateTime;

public interface SupplyItemView {

    Long getId();

    String getIdentificationNumber();

    String getComment();

    LocalDateTime getArrivalDate();

    StoreView getStore();

    ProviderItemView getProvider();
}
