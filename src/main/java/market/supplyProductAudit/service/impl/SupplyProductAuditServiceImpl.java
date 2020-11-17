package market.supplyProductAudit.service.impl;

import lombok.RequiredArgsConstructor;
import market.entity.Supply;
import market.entity.SupplyProductAudit;
import market.supplyProductAudit.SupplyProductAuditRepository;
import market.supplyProductAudit.dto.SupplyProductAuditDto;
import market.supplyProductAudit.service.SupplyProductAuditService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplyProductAuditServiceImpl implements SupplyProductAuditService {

    private final SupplyProductAuditRepository supplyProductAuditRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public List<SupplyProductAudit> create(List<SupplyProductAuditDto> supplyProductAuditDtos, Supply supply) {
        List<SupplyProductAudit> audits = this.modelMapper.map(supplyProductAuditDtos,
                new TypeToken<List<SupplyProductAudit>>() {}.getType());
        for (SupplyProductAudit audit : audits) {
            audit.setSupply(supply);
        }
        return this.supplyProductAuditRepository.saveAll(audits);
    }
}
