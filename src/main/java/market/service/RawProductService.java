package market.service;

import market.dto.rawProduct.RawProductDto;
import market.dto.uiProduct.UIProductDto;
import market.entity.RawProduct;

import java.util.List;

public interface RawProductService {

    List<RawProduct> getAll();

    RawProduct findOneById(Long id);

    RawProduct create(RawProductDto rawProductDto);

    RawProduct update(RawProductDto rawProductDto);

    void bulkDelete(List<Long> ids);

}
