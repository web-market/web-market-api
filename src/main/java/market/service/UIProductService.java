package market.service;

import market.dto.uiProduct.UIProductDto;
import market.entity.UIProduct;
import market.projection.uiProduct.UIProductView;

import java.util.List;

public interface UIProductService {

    //Projections
    List<UIProductView> getAllUIProductsByNameLike(String name);

    List<UIProductView> getAllUIProducts();

    //TODO: Stanislav should take a look to this one
    //List<UIProduct> getAllByCategories(List<Long> ids);

    UIProductView getUIProduct(Long id);

    //DTOs
    UIProduct create(UIProductDto UIProductDto);

    UIProduct update(UIProductDto UIProductDto);

    void bulkDelete(List<Long> ids);
}
