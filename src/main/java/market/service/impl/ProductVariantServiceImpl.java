package market.service.impl;

import lombok.RequiredArgsConstructor;
import market.dto.productVariant.ProductVariantDto;
import market.entity.ProductVariant;
import market.projection.productVariant.ProductVariantView;
import market.repository.ProductVariantRepository;
import market.service.FilterValueService;
import market.service.ProductVariantService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {

    private final FilterValueService filterValueService;
    private final ProductVariantRepository productVariantRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<ProductVariantView> getProductVariants() {
        return this.productVariantRepository.findAllBy();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductVariantView> getProductVariantsByFilterValues(List<Long> filterValueIds) {
        return this.productVariantRepository.findAllByFilterValuesIdIn(filterValueIds);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductVariantView getProductVariant(Long id) {
        return this.productVariantRepository.findOneById(id);
    }

    @Override
    @Transactional
    public ProductVariant create(ProductVariantDto productVariantDto) {
        ProductVariant newProductVariant = this.modelMapper.map(productVariantDto, ProductVariant.class);
        newProductVariant.setFilterValues(this.filterValueService.getFullMentionedFilterValues(productVariantDto.getFilterValueIds()));
        return this.productVariantRepository.save(newProductVariant);
    }

    @Override
    @Transactional
    public ProductVariant update(ProductVariantDto productVariantDto) {
        ProductVariant productVariant = this.modelMapper.map(productVariantDto, ProductVariant.class);
        productVariant.setFilterValues(this.filterValueService.getFullMentionedFilterValues(productVariantDto.getFilterValueIds()));
        return this.productVariantRepository.save(productVariant);
    }

    @Override
    @Transactional
    public void bulkDelete(List<Long> ids) {
        this.productVariantRepository.deleteAllByIdIn(ids);
    }
}
