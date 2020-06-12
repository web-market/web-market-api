package market.service.impl;

import lombok.RequiredArgsConstructor;
import market.dto.product.ProductDto;
import market.entity.Category;
import market.entity.FilterValue;
import market.entity.Manufacturer;
import market.entity.Product;
import market.repository.ProductRepository;
import market.service.CategoryService;
import market.service.FilterValueService;
import market.service.ManufacturerService;
import market.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CategoryService categoryService;
    private final FilterValueService filterValueService;
    private final ManufacturerService manufacturerService;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product findOneById(Long id) {
        return this.productRepository.findOneById(id);
    }

    @Override
    @Transactional
    public Product create(ProductDto productDto) {
        Product newProduct = this.modelMapper.map(productDto, Product.class);
        if (productDto.getManufacturerId() != null) {
            Manufacturer manufacturer = this.manufacturerService.findOneById(productDto.getManufacturerId());
            newProduct.setManufacturer(manufacturer);
        }
        if (productDto.getCategoryIds() != null) {
            List<Category> categories = this.categoryService.getByIdIn(productDto.getCategoryIds());
            newProduct.setCategories(categories);
        }
        if (productDto.getFilterValueIds() != null) {
            List<FilterValue> filterValues = this.filterValueService.getByIdIn(productDto.getFilterValueIds());
            newProduct.setFilterValues(filterValues);
        }
        return this.productRepository.save(newProduct);
    }

    @Override
    @Transactional
    public Product update(ProductDto productDto) {
        Product product = this.modelMapper.map(productDto, Product.class);
        if (productDto.getManufacturerId() != null) {
            Manufacturer manufacturer = this.manufacturerService.findOneById(productDto.getManufacturerId());
            product.setManufacturer(manufacturer);
        }
        if (productDto.getCategoryIds() != null) {
            List<Category> categories = this.categoryService.getByIdIn(productDto.getCategoryIds());
            product.setCategories(categories);
        }
        if (productDto.getFilterValueIds() != null) {
            List<FilterValue> filterValues = this.filterValueService.getByIdIn(productDto.getFilterValueIds());
            product.setFilterValues(filterValues);
        }
        return this.productRepository.save(product);
    }

    @Override
    @Transactional
    public void bulkDelete(List<Long> ids) {
        this.productRepository.deleteAllByIdIn(ids);
    }

    @Override
    public List<Product> getAllByCategories(List<Long> ids) {
        return this.productRepository.getAllByCategoriesIdIn(ids);
    }

    @Override
    public List<Product> getAllByFilterValues(List<Long> ids) {
        return this.productRepository.getAllByFilterValuesIdIn(ids);
    }

    @Override
    public List<Product> getAllByNameLike(String name) {
        return this.productRepository.getAllByNameContainingIgnoreCase(name);
    }

}
