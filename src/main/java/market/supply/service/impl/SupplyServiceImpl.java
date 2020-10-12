package market.supply.service.impl;

import lombok.RequiredArgsConstructor;
import market.entity.Supply;
import market.entity.SupplyStatus;
import market.supply.SupplyRepository;
import market.supply.dto.SupplyCompositeDto;
import market.supply.dto.SupplyItemView;
import market.supply.service.SupplyService;
import market.supplyRawProductAudit.SupplyRawProductAuditService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplyServiceImpl implements SupplyService {

    private final SupplyRepository supplyRepository;
    private final ModelMapper modelMapper;
    private final SupplyRawProductAuditService supplyRawProductAuditService;

    @Override
    public List<SupplyItemView> getSupplies() {
        return this.supplyRepository.findAllBy();
    }

    @Override
    public SupplyItemView getSupply(Long id) {
        return this.supplyRepository.getById(id);
    }

    @Override
    public void create(SupplyCompositeDto supplyCompositeDto) {
        var supply = this.modelMapper.map(supplyCompositeDto, Supply.class);
        supply.setStatus(SupplyStatus.DRAFT);
        this.supplyRawProductAuditService.create(supplyCompositeDto.getSupplyRawProductAudit(),
                this.supplyRepository.save(supply));
    }


}
