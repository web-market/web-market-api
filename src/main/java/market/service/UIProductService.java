package market.service;

import market.dto.uiProduct.UIProductDto;
import market.entity.UIProduct;

import java.util.List;

public interface UIProductService {

    List<UIProduct> getAll();

    UIProduct findOneById(Long id);

    UIProduct create(UIProductDto UIProductDto);

    UIProduct update(UIProductDto UIProductDto);

    void bulkDelete(List<Long> ids);

//    List<UIProduct> getAllByCategories(List<Long> ids);
//
//    List<UIProduct> getAllByFilterValues(List<Long> ids);

    List<UIProduct> getAllByNameLike(String name);
}
