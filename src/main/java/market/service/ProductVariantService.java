package market.service;

import market.dto.productVariant.ProductVariantDto;
import market.entity.ProductVariant;
import market.projection.productVariant.ProductVariantView;

import java.util.List;

public interface ProductVariantService {

    //Projections
    List<ProductVariantView> getProductVariants();

    List<ProductVariantView> getProductVariantsByFilterValues(List<Long> filterValueIds);

    ProductVariantView getProductVariant(Long id);

    //DTOs
    ProductVariant create(ProductVariantDto productVariantDto);

    ProductVariant update(ProductVariantDto productVariantDto);

    void bulkDelete(List<Long> ids);

}
