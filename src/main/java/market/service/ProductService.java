package market.service;

import market.dto.product.ProductDto;
import market.entity.Product;
import java.util.List;

public interface ProductService {

    List<Product> getAll();

    Product findOneById(Long id);

    Product create(ProductDto productDto);

    Product update(ProductDto productDto);

    void bulkDelete(List<Long> ids);

    List<Product> getAllByCategories(List<Long> ids);

    List<Product> getAllByFilterValues(List<Long> ids);

}
