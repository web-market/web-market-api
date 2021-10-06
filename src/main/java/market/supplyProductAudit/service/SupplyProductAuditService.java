package market.supplyProductAudit.service;

import market.entity.Supply;
import market.entity.SupplyProductAudit;
import market.supplyProductAudit.dto.SupplyProductAuditDto;

import java.util.List;

public interface SupplyProductAuditService {

    List<SupplyProductAudit> create(List<SupplyProductAuditDto> supplyProductAuditDtos, Supply supply);

    void deleteBySupplyId(Long supplyId);
}
