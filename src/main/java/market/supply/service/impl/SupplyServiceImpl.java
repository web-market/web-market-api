package market.supply.service.impl;

import lombok.RequiredArgsConstructor;
import market.entity.Supply;
import market.entity.SupplyStatus;
import market.supply.SupplyRepository;
import market.supply.dto.SupplyCompositeDto;
import market.supply.dto.SupplyCompositeItemView;
import market.supply.dto.SupplyItemView;
import market.supply.service.SupplyService;
import market.supplyRawProductAudit.SupplyRawProductAuditService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplyServiceImpl implements SupplyService {

    private final SupplyRepository supplyRepository;
    private final ModelMapper modelMapper;
    private final SupplyRawProductAuditService supplyRawProductAuditService;

    @Override
    @Transactional(readOnly = true)
    public List<SupplyItemView> getSupplies() {
        return this.supplyRepository.findAllBy();
    }

    @Override
    @Transactional(readOnly = true)
    public SupplyItemView getSupply(Long id) {
        return this.supplyRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public SupplyCompositeItemView getSupplyCompositeItemViewById(Long id) {
        return this.supplyRepository.getSupplyCompositeItemViewById(id);
    }

    @Override
    @Transactional
    public Supply create(SupplyCompositeDto supplyCompositeDto) {
        var supply = this.modelMapper.map(supplyCompositeDto, Supply.class);
        supply.setStatus(SupplyStatus.DRAFT);
        supply = this.supplyRepository.save(supply);
        this.supplyRawProductAuditService.create(supplyCompositeDto.getSupplyRawProductAudit(), supply);
        return supply;
    }

    @Override
    @Transactional
    public Boolean isIdentificationNumberUnique(String identificationNumber) {
        return !this.supplyRepository.existsByIdentificationNumber(identificationNumber);
    }

}
