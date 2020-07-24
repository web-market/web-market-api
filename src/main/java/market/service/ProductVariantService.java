package market.service;

import market.dto.productVariant.ProductVariantDto;
import market.entity.ProductVariant;
import market.entity.UIProduct;

import java.util.List;

public interface ProductVariantService {

    List<ProductVariant> getAll();

    ProductVariant findOneById(Long id);

    List<ProductVariant> getAllByFilterValues(List<Long> ids);

    ProductVariant create(ProductVariantDto productVariantDto);

    ProductVariant update(ProductVariantDto productVariantDto);

    void bulkDelete(List<Long> ids);

}
