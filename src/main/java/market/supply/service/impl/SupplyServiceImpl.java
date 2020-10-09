package market.supply.service.impl;

import lombok.RequiredArgsConstructor;
import market.supply.SupplyRepository;
import market.supply.dto.SupplyItemView;
import market.supply.service.SupplyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplyServiceImpl implements SupplyService {

    private final SupplyRepository supplyRepository;

    @Override
    public List<SupplyItemView> getSupplies() {
        return this.supplyRepository.findAllBy();
    }

    @Override
    public SupplyItemView getSupply(Long id) {
        return this.supplyRepository.getById(id);
    }

}
