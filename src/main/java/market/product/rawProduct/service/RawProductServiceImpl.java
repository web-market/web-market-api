package market.product.rawProduct.service;

import lombok.RequiredArgsConstructor;
import market.entity.RawProduct;
import market.manufacturer.service.ManufacturerService;
import market.product.rawProduct.dto.RawProductDto;
import market.product.rawProduct.dto.RawProductView;
import market.repository.RawProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RawProductServiceImpl implements RawProductService {

    private final RawProductRepository rawProductRepository;
    private final ManufacturerService manufacturerService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<RawProductView> getAllRawProducts() {
        return this.rawProductRepository.findAllBy();
    }

    @Override
    @Transactional(readOnly = true)
    public RawProductView getRawProduct(Long id) {
        return this.rawProductRepository.findOneById(id);
    }

    @Override
    @Transactional
    public RawProduct create(RawProductDto rawProductDto) {
        RawProduct newRawProduct = this.modelMapper.map(rawProductDto, RawProduct.class);
        newRawProduct.setManufacturer(this.manufacturerService.getFullManufacturer(rawProductDto.getManufacturerId()));
        return this.rawProductRepository.save(newRawProduct);
    }

    @Override
    @Transactional
    public RawProduct update(RawProductDto rawProductDto) {
        RawProduct rawProduct = this.modelMapper.map(rawProductDto, RawProduct.class);
        rawProduct.setManufacturer(this.manufacturerService.getFullManufacturer(rawProductDto.getManufacturerId()));
        return this.rawProductRepository.save(rawProduct);
    }

    @Override
    @Transactional
    public void bulkDelete(List<Long> ids) {
        this.rawProductRepository.deleteAllByIdIn(ids);
    }
}
