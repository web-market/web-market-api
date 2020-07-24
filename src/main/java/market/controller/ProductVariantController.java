package market.controller;

import lombok.RequiredArgsConstructor;
import market.dto.BulkDeleteDto;
import market.dto.productVariant.ProductVariantDto;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.entity.ProductVariant;
import market.service.ProductVariantService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/product-variants")
@RequiredArgsConstructor
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    @GetMapping
    public List<ProductVariant> getAll() {
        return this.productVariantService.getAll();
    }

    @GetMapping("/{id}")
    public ProductVariant getById(@PathVariable Long id) {
        return this.productVariantService.findOneById(id);
    }

    @PostMapping
    public ProductVariant create(@Validated(Create.class) @RequestBody ProductVariantDto productVariantDto) {
        return this.productVariantService.create(productVariantDto);
    }

    @PutMapping
    public ProductVariant update(@Validated(Update.class) @RequestBody ProductVariantDto productVariantDto) {
        return this.productVariantService.update(productVariantDto);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@Valid @RequestBody BulkDeleteDto bulkDeleteDto) {
        this.productVariantService.bulkDelete(bulkDeleteDto.getIds());
        return ResponseEntity.ok("deleted successfully");
    }
}
