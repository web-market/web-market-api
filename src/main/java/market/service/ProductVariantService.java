package market.service;

import market.dto.productVariant.ProductVariantDto;
import market.entity.ProductVariant;

import java.util.List;

public interface ProductVariantService {

    List<ProductVariant> getProductVariants();

    List<ProductVariant> getProductVariantsByFilterValues(List<Long> filterValueIds);

    ProductVariant getProductVariant(Long id);

    //DTOs
    ProductVariant create(ProductVariantDto productVariantDto);

    ProductVariant update(ProductVariantDto productVariantDto);

    void bulkDelete(List<Long> ids);

}
