package market.service.impl;

import lombok.RequiredArgsConstructor;
import market.projection.supply.SupplyItemView;
import market.repository.SupplyRepository;
import market.service.SupplyService;
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
