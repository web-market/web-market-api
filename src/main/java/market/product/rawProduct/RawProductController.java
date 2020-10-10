package market.product.rawProduct;

import lombok.RequiredArgsConstructor;
import market.dto.BulkDeleteDto;
import market.product.rawProduct.dto.RawProductDto;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.entity.RawProduct;
import market.product.rawProduct.dto.RawProductView;
import market.product.rawProduct.service.RawProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/raw-product-management")
@RequiredArgsConstructor
public class RawProductController {

    private final RawProductService rawProductService;

    @GetMapping("/raw-products")
    public ResponseEntity<List<RawProductView>> getAll() {
        return new ResponseEntity<>(this.rawProductService.getAllRawProducts(), HttpStatus.OK);
    }

    @GetMapping("/raw-products/{id}")
    public ResponseEntity<RawProductView> getById(@PathVariable Long id) {
        return new ResponseEntity<>(this.rawProductService.getRawProduct(id), HttpStatus.OK);
    }

    @PostMapping("/raw-products")
    public ResponseEntity<RawProduct> create(@Validated(Create.class) @RequestBody RawProductDto rawProductDto) {
        return new ResponseEntity<>(this.rawProductService.create(rawProductDto), HttpStatus.CREATED);
    }

    @PutMapping("/raw-products")
    public ResponseEntity<RawProduct> update(@Validated(Update.class) @RequestBody RawProductDto rawProductDto) {
        return new ResponseEntity<>(this.rawProductService.update(rawProductDto), HttpStatus.OK);
    }

    @DeleteMapping("/raw-products")
    public ResponseEntity<String> delete(@Valid @RequestBody BulkDeleteDto bulkDeleteDto) {
        this.rawProductService.bulkDelete(bulkDeleteDto.getIds());
        return ResponseEntity.ok("deleted successfully");
    }
}
