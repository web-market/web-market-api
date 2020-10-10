package market.supplyRawProductAudit;

import lombok.RequiredArgsConstructor;
import market.entity.Supply;
import market.entity.SupplyRawProductAudit;
import market.product.rawProduct.dto.SupplyRawProductAuditDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplyRawProductAuditServiceImpl implements SupplyRawProductAuditService {

    private final SupplyRawProductAuditRepository supplyRawProductAuditRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<SupplyRawProductAudit> create(List<SupplyRawProductAuditDto> supplyRawProductAuditDtos, Supply supply) {
        List<SupplyRawProductAudit> audits = this.modelMapper.map(supplyRawProductAuditDtos,
                new TypeToken<List<SupplyRawProductAudit>>() {}.getType());
        for (SupplyRawProductAudit audit : audits) {
            audit.setSupply(supply);
        }
        return this.supplyRawProductAuditRepository.saveAll(audits);
    }
}