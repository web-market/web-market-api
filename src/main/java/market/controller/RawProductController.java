package market.controller;

import lombok.RequiredArgsConstructor;
import market.dto.BulkDeleteDto;
import market.dto.rawProduct.RawProductDto;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.entity.RawProduct;
import market.service.RawProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/raw-products")
@RequiredArgsConstructor
public class RawProductController {

    private final RawProductService rawProductService;

    @GetMapping
    public List<RawProduct> getAll() {
        return this.rawProductService.getAll();
    }

    @GetMapping("/{id}")
    public RawProduct getById(@PathVariable Long id) {
        return this.rawProductService.findOneById(id);
    }

    @PostMapping
    public RawProduct create(@Validated(Create.class) @RequestBody RawProductDto rawProductDto) {
        return this.rawProductService.create(rawProductDto);
    }

    @PutMapping
    public RawProduct update(@Validated(Update.class) @RequestBody RawProductDto rawProductDto) {
        return this.rawProductService.update(rawProductDto);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@Valid @RequestBody BulkDeleteDto bulkDeleteDto) {
        this.rawProductService.bulkDelete(bulkDeleteDto.getIds());
        return ResponseEntity.ok("deleted successfully");
    }
}
