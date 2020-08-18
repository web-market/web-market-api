package market.controller;

import lombok.RequiredArgsConstructor;
import market.dto.BulkDeleteDto;
import market.dto.rawProduct.RawProductDto;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.entity.RawProduct;
import market.projection.rawProduct.RawProductView;
import market.service.RawProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//TODO: URL naming and methods naming
@RestController
@RequestMapping(path = "/raw-products")
@RequiredArgsConstructor
public class RawProductController {

    private final RawProductService rawProductService;

    @GetMapping
    public ResponseEntity<List<RawProductView>> getAll() {
        return new ResponseEntity<>(this.rawProductService.getAllRawProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RawProductView> getById(@PathVariable Long id) {
        return new ResponseEntity<>(this.rawProductService.getRawProduct(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RawProduct> create(@Validated(Create.class) @RequestBody RawProductDto rawProductDto) {
        return new ResponseEntity<>(this.rawProductService.create(rawProductDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RawProduct> update(@Validated(Update.class) @RequestBody RawProductDto rawProductDto) {
        return new ResponseEntity<>(this.rawProductService.update(rawProductDto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@Valid @RequestBody BulkDeleteDto bulkDeleteDto) {
        this.rawProductService.bulkDelete(bulkDeleteDto.getIds());
        return ResponseEntity.ok("deleted successfully");
    }
}
