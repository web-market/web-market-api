package market.supply.dto;

import market.entity.SupplyStatus;
import market.provider.dto.ProviderItemView;
import market.storeProductSupplyAudit.dto.StoreProductSupplyAuditItemView;

import java.time.LocalDateTime;
import java.util.List;

public interface SupplySeparationInfoView {

    Long getId();

    String getIdentificationNumber();

    String getComment();

    LocalDateTime getArrivalDate();

    ProviderItemView getProvider();

    List<StoreProductSupplyAuditItemView> getStoreProductSupplyAudits();

    SupplyStatus getStatus();

}
