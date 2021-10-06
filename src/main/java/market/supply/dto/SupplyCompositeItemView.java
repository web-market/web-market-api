package market.supply.dto;

import market.entity.SupplyStatus;
import market.provider.dto.ProviderItemView;
import market.store.dto.StoreView;
import market.supplyProductAudit.dto.SupplyProductAuditItemView;

import java.time.LocalDateTime;
import java.util.List;

public interface SupplyCompositeItemView {

    Long getId();

    String getIdentificationNumber();

    String getComment();

    LocalDateTime getArrivalDate();

    ProviderItemView getProvider();

    List<SupplyProductAuditItemView> getSupplyProductAudits();

    SupplyStatus getStatus();
}
