package market.service;

import market.dto.rawProduct.RawProductDto;
import market.entity.RawProduct;
import market.projection.rawProduct.RawProductView;

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
