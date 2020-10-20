package market.supplyRawProductAudit.service;

import market.entity.Supply;
import market.entity.SupplyProductAudit;
import market.supplyRawProductAudit.dto.SupplyProductAuditDto;

import java.util.List;

public interface SupplyProductAuditService {

    List<SupplyProductAudit> create(List<SupplyProductAuditDto> supplyProductAuditDtos, Supply supply);

}
