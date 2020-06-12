package market.service.impl;

import lombok.RequiredArgsConstructor;
import market.entity.Product;
import market.repository.ProductRepository;
import market.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

}
