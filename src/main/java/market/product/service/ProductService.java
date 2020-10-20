package market.product.service;

import market.entity.Product;
import market.product.dto.ProductDto;
import market.product.dto.ProductView;

import java.util.List;

public interface ProductService {

    //Projections
    List<ProductView> getProductVariants();

    List<ProductView> getProductVariantsByFilterValues(List<Long> filterValueIds);

    ProductView getProductVariant(Long id);

    //DTOs
    Product create(ProductDto productDto);

    Product update(ProductDto productDto);

    void bulkDelete(List<Long> ids);

}
