package market.product.controller;

import lombok.RequiredArgsConstructor;
import market.dto.BulkDeleteDto;
import market.entity.Product;
import market.product.dto.ProductDto;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.product.dto.ProductView;
import market.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/product-management")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductView>> getAll() {
        return new ResponseEntity<>(this.productService.getProductVariants(), HttpStatus.OK);
    }

    //TODO: These path variable should be discussed
    @GetMapping("/products/filter-value/{ids}")
    public ResponseEntity<List<ProductView>> getByFilterValues(@PathVariable Long[] ids) {
        return new ResponseEntity<>(this.productService.getProductVariantsByFilterValues(Arrays.asList(ids)), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductView> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(this.productService.getProductVariant(id), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> create(@Validated(Create.class) @RequestBody ProductDto productDto) {
        return new ResponseEntity<>(this.productService.create(productDto), HttpStatus.CREATED);
    }

    @PutMapping("/products")
    public ResponseEntity<Product> update(@Validated(Update.class) @RequestBody ProductDto productDto) {
        return new ResponseEntity<>(this.productService.update(productDto), HttpStatus.OK);
    }

    @DeleteMapping("/products")
    public ResponseEntity<String> delete(@Valid @RequestBody BulkDeleteDto bulkDeleteDto) {
        this.productService.bulkDelete(bulkDeleteDto.getIds());
        return ResponseEntity.ok("deleted successfully");
    }
}
