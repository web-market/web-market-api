package market.service;

import market.dto.productVariant.ProductVariantDto;
import market.dto.uiProduct.UIProductDto;
import market.entity.ProductVariant;
import market.entity.UIProduct;

import java.util.List;

public interface ProductVariantService {

    List<ProductVariant> getAll();

    ProductVariant findOneById(Long id);

    ProductVariant create(ProductVariantDto productVariantDto);

    ProductVariant update(ProductVariantDto productVariantDto);

    void bulkDelete(List<Long> ids);

}
