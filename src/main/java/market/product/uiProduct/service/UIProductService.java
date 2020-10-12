package market.product.uiProduct.service;

import market.entity.UIProduct;
import market.product.uiProduct.dto.UIProductDto;
import market.product.uiProduct.dto.UIProductView;

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
