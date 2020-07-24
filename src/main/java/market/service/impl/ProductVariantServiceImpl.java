package market.service.impl;

import lombok.RequiredArgsConstructor;
import market.dto.productVariant.ProductVariantDto;
import market.entity.ProductVariant;
import market.repository.ProductVariantRepository;
import market.service.ProductVariantService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {

    private final ProductVariantRepository productVariantRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<ProductVariant> getAll() {
        return this.productVariantRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductVariant findOneById(Long id) {
        return this.productVariantRepository.findOneById(id);
    }

    @Override
    @Transactional
    public ProductVariant create(ProductVariantDto productVariantDto) {
        ProductVariant newProductVariant = this.modelMapper.map(productVariantDto, ProductVariant.class);
        return this.productVariantRepository.save(newProductVariant);
    }

    @Override
    @Transactional
    public ProductVariant update(ProductVariantDto productVariantDto) {
        ProductVariant productVariant = this.modelMapper.map(productVariantDto, ProductVariant.class);
        return this.productVariantRepository.save(productVariant);
    }

    @Override
    @Transactional
    public void bulkDelete(List<Long> ids) {
        this.productVariantRepository.deleteAllByIdIn(ids);
    }
}
