package market.controller;

import lombok.RequiredArgsConstructor;
import market.dto.BulkDeleteDto;
import market.dto.productVariant.ProductVariantDto;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.entity.ProductVariant;
import market.projection.productVariant.ProductVariantView;
import market.service.ProductVariantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

//TODO: URL naming and methods naming
@RestController
@RequestMapping(path = "/product-variants")
@RequiredArgsConstructor
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    @GetMapping
    public ResponseEntity<List<ProductVariantView>> getAll() {
        return new ResponseEntity<>(this.productVariantService.getProductVariants(), HttpStatus.OK);
    }

    //TODO: These path variable should be discussed
    @GetMapping("/by-filter-values/{ids}")
    public ResponseEntity<List<ProductVariantView>> getByFilerValueIds(@PathVariable Long[] ids) {
        return new ResponseEntity<>(this.productVariantService.getProductVariantsByFilterValues(Arrays.asList(ids)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductVariantView> getById(@PathVariable Long id) {
        return new ResponseEntity<>(this.productVariantService.getProductVariant(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductVariant> create(@Validated(Create.class) @RequestBody ProductVariantDto productVariantDto) {
        return new ResponseEntity<>(this.productVariantService.create(productVariantDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductVariant> update(@Validated(Update.class) @RequestBody ProductVariantDto productVariantDto) {
        return new ResponseEntity<>(this.productVariantService.update(productVariantDto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@Valid @RequestBody BulkDeleteDto bulkDeleteDto) {
        this.productVariantService.bulkDelete(bulkDeleteDto.getIds());
        return ResponseEntity.ok("deleted successfully");
    }
}
