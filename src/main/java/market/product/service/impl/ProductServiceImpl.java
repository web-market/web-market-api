package market.product.service.impl;

import lombok.RequiredArgsConstructor;
import market.entity.Product;
import market.filterValue.service.FilterValueService;
import market.manufacturer.service.ManufacturerService;
import market.model.service.ModelService;
import market.product.ProductRepository;
import market.product.dto.ProductDto;
import market.product.dto.ProductView;
import market.product.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final FilterValueService filterValueService;
    private final ManufacturerService manufacturerService;
    private final ModelService modelService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProductView> getProductVariants() {
        return this.productRepository.findAllBy();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductView> getProductVariantsByFilterValues(List<Long> filterValueIds) {
        return this.productRepository.findAllByFilterValuesIdIn(filterValueIds);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductView getProductVariant(Long id) {
        return this.productRepository.findOneById(id);
    }

    @Override
    @Transactional
    public Product create(ProductDto productDto) {
        var newProduct = this.modelMapper.map(productDto, Product.class);
        newProduct.setFilterValues(this.filterValueService.getFullFilterValues(productDto.getFilterValueIds()));
        newProduct.setManufacturer(this.manufacturerService.getFullManufacturer(productDto.getManufacturerId()));
        newProduct.setModel(this.modelService.getFullModel(productDto.getModelId()));
        return this.productRepository.save(newProduct);
    }

    @Override
    @Transactional
    public Product update(ProductDto productDto) {
        var product = this.modelMapper.map(productDto, Product.class);
        product.setFilterValues(this.filterValueService.getFullFilterValues(productDto.getFilterValueIds()));
        product.setManufacturer(this.manufacturerService.getFullManufacturer(productDto.getManufacturerId()));
        product.setModel(this.modelService.getFullModel(productDto.getModelId()));
        return this.productRepository.save(product);
    }

    @Override
    @Transactional
    public void bulkDelete(List<Long> ids) {
        this.productRepository.deleteAllByIdIn(ids);
    }
}
