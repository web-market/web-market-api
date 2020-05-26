package market.controller;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import market.dto.BulkDeleteDto;
import market.dto.product.ProductDto;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.entity.Product;
import market.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping(path = "/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return this.productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return this.productService.findOneById(id);
    }

    @GetMapping("/by-categories/{ids}")
    public List<Product> getByCategoryIds(@PathVariable Long[] ids) {
        return this.productService.getAllByCategories(Arrays.asList(ids));
    }

    @GetMapping("/by-filter-values/{ids}")
    public List<Product> getByFilerValueIds(@PathVariable Long[] ids) {
        return this.productService.getAllByFilterValues(Arrays.asList(ids));
    }

    @PostMapping
    public Product create(@Validated(Create.class) @RequestBody ProductDto productDto) {
        return this.productService.create(productDto);
    }

    @PutMapping
    public Product update(@Validated(Update.class) @RequestBody ProductDto productDto) {
        return this.productService.update(productDto);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@Valid @RequestBody BulkDeleteDto bulkDeleteDto) {
        this.productService.bulkDelete(bulkDeleteDto.getIds());
        return ResponseEntity.ok("deleted successfully");
    }
}
