package market.supply.dto;

import market.entity.SupplyStatus;
import market.provider.dto.ProviderItemView;
import market.store.dto.StoreView;

import java.time.LocalDateTime;

public interface SupplyItemView {

    Long getId();

    String getIdentificationNumber();

    String getComment();

    LocalDateTime getArrivalDate();

    ProviderItemView getProvider();

    SupplyStatus getStatus();
}
