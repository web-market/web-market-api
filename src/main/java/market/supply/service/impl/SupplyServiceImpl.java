package market.supply.service.impl;

import lombok.RequiredArgsConstructor;
import market.entity.ActualStoreState;
import market.entity.Supply;
import market.entity.SupplyStatus;
import market.exceptions.ProcessCompletedSupplyException;
import market.storeProductSupplyAudit.service.StoreProductSupplyAuditService;
import market.supply.SupplyRepository;
import market.supply.dto.*;
import market.supply.service.SupplyService;
import market.supplyProductAudit.service.SupplyProductAuditService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

//Add getting supply buy exact date and status
@Service
@RequiredArgsConstructor
public class SupplyServiceImpl implements SupplyService {

    private final SupplyRepository supplyRepository;
    private final ModelMapper modelMapper;
    private final SupplyProductAuditService supplyProductAuditService;
    private final StoreProductSupplyAuditService storeProductSupplyAuditService;

    @Override
    @Transactional(readOnly = true)
    public List<SupplyItemView> getSupplies() {
        return this.supplyRepository.findAllBy();
    }

    @Override
    @Transactional
    public List<SupplyItemView> getSuppliesByStatusAndArrivalDate(String status, LocalDateTime date) {
        return this.supplyRepository.findAllByStatusAndArrivalDate(SupplyStatus.valueOf(status), date);
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
    public SupplySeparationInfoView getSupplySeparationInfo(Long id) {
        return this.supplyRepository.getSupplySeparationInfoViewById(id);
    }

    //May be to send only supply without composite dto
    @Override
    @Transactional
    public Supply create(SupplyCompositeDto supplyCompositeDto) {
        var supply = this.modelMapper.map(supplyCompositeDto, Supply.class);
        supply.setStatus(SupplyStatus.DRAFT);
        supply = this.supplyRepository.save(supply);
        this.supplyProductAuditService.create(supplyCompositeDto.getSupplyProductAudit(), supply);
        return supply;
    }

    @Override
    @Transactional
    public Boolean isIdentificationNumberUnique(String identificationNumber) {
        return !this.supplyRepository.existsByIdentificationNumber(identificationNumber);
    }

    //As for now full supply processing (throw exception if products separated wrongly)
    @Override
    @Transactional
    public List<ActualStoreState> processSupply(SupplyProcessingDto supplyProcessingDto) {
        var supply = this.supplyRepository.getSupplyById(supplyProcessingDto.getId());
        if (supply.getStatus().equals(SupplyStatus.COMPLETE)) {
            throw new ProcessCompletedSupplyException("");
        }
        var storeProductSupplyAudits = this.storeProductSupplyAuditService.create(supplyProcessingDto.getPlacementInStores(), supply);
        supply.setStatus(SupplyStatus.COMPLETE);
        this.supplyRepository.save(supply);
        return storeProductSupplyAudits;
    }

    //In future bulk delete for multiple supplies
    @Override
    @Transactional
    public void delete(Long id) {
        if (this.supplyRepository.findStatusById(id).equals(SupplyStatus.COMPLETE)) {
            throw new ProcessCompletedSupplyException("");
        }
        this.supplyProductAuditService.deleteBySupplyId(id);
        this.supplyRepository.deleteById(id);
    }

}
