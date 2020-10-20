package market.supply.dto;

import market.provider.dto.ProviderItemView;
import market.store.dto.StoreView;
import market.supplyRawProductAudit.dto.SupplyProductAuditItemView;

import java.time.LocalDateTime;
import java.util.List;

public interface SupplyCompositeItemView {

    Long getId();

    String getIdentificationNumber();

    String getComment();

    LocalDateTime getArrivalDate();

    StoreView getStore();

    ProviderItemView getProvider();

    List<SupplyProductAuditItemView> getSupplyProductAudits();

}
