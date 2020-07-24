package market.service.impl;

import lombok.RequiredArgsConstructor;
import market.dto.rawProduct.RawProductDto;
import market.entity.RawProduct;
import market.repository.RawProductRepository;
import market.service.RawProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RawProductServiceImpl implements RawProductService {

    private final RawProductRepository rawProductRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<RawProduct> getAll() {
        return this.rawProductRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public RawProduct findOneById(Long id) {
        return this.rawProductRepository.findOneById(id);
    }

    @Override
    @Transactional
    public RawProduct create(RawProductDto rawProductDto) {
        RawProduct newRawProduct = this.modelMapper.map(rawProductDto, RawProduct.class);
        return this.rawProductRepository.save(newRawProduct);
    }

    @Override
    @Transactional
    public RawProduct update(RawProductDto rawProductDto) {
        RawProduct RawProduct = this.modelMapper.map(rawProductDto, RawProduct.class);
        return this.rawProductRepository.save(RawProduct);
    }

    @Override
    @Transactional
    public void bulkDelete(List<Long> ids) {
        this.rawProductRepository.deleteAllByIdIn(ids);
    }
}
