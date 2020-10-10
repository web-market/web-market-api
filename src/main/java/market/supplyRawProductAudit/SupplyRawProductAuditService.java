package market.supplyRawProductAudit;

import market.entity.Supply;
import market.entity.SupplyRawProductAudit;
import market.product.rawProduct.dto.SupplyRawProductAuditDto;

import java.util.List;

public interface SupplyRawProductAuditService {

    List<SupplyRawProductAudit> create(List<SupplyRawProductAuditDto> supplyRawProductAuditDtos, Supply supply);

}
