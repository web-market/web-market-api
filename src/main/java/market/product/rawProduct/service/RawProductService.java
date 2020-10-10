package market.product.rawProduct.service;

import market.product.rawProduct.dto.RawProductDto;
import market.entity.RawProduct;
import market.product.rawProduct.dto.RawProductView;

import java.util.List;

public interface RawProductService {

    //Projections
    List<RawProductView> getAllRawProducts();

    RawProductView getRawProduct(Long id);

    //DTOs
    RawProduct create(RawProductDto rawProductDto);

    RawProduct update(RawProductDto rawProductDto);

    void bulkDelete(List<Long> ids);

}
