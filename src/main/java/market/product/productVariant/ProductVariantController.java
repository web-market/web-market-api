package market.product.productVariant;

import lombok.RequiredArgsConstructor;
import market.dto.BulkDeleteDto;
import market.product.productVariant.dto.ProductVariantDto;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.entity.ProductVariant;
import market.product.productVariant.dto.ProductVariantView;
import market.product.productVariant.service.ProductVariantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/product-variant-management")
@RequiredArgsConstructor
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    @GetMapping("/product-variants")
    public ResponseEntity<List<ProductVariantView>> getAll() {
        return new ResponseEntity<>(this.productVariantService.getProductVariants(), HttpStatus.OK);
    }

    //TODO: These path variable should be discussed
    @GetMapping("/product-variants/filter-value/{ids}")
    public ResponseEntity<List<ProductVariantView>> getByFilerValues(@PathVariable Long[] ids) {
        return new ResponseEntity<>(this.productVariantService.getProductVariantsByFilterValues(Arrays.asList(ids)), HttpStatus.OK);
    }

    @GetMapping("/product-variants/{id}")
    public ResponseEntity<ProductVariantView> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(this.productVariantService.getProductVariant(id), HttpStatus.OK);
    }

    @PostMapping("/product-variants")
    public ResponseEntity<ProductVariant> create(@Validated(Create.class) @RequestBody ProductVariantDto productVariantDto) {
        return new ResponseEntity<>(this.productVariantService.create(productVariantDto), HttpStatus.CREATED);
    }

    @PutMapping("/product-variants")
    public ResponseEntity<ProductVariant> update(@Validated(Update.class) @RequestBody ProductVariantDto productVariantDto) {
        return new ResponseEntity<>(this.productVariantService.update(productVariantDto), HttpStatus.OK);
    }

    @DeleteMapping("/product-variants")
    public ResponseEntity<String> delete(@Valid @RequestBody BulkDeleteDto bulkDeleteDto) {
        this.productVariantService.bulkDelete(bulkDeleteDto.getIds());
        return ResponseEntity.ok("deleted successfully");
    }
}
