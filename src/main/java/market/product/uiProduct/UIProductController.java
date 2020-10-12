package market.product.uiProduct;

import lombok.RequiredArgsConstructor;
import market.dto.BulkDeleteDto;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.entity.UIProduct;
import market.product.uiProduct.dto.UIProductDto;
import market.product.uiProduct.dto.UIProductView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

//TODO: URL naming and methods naming
@RestController
@RequestMapping(path = "/products")
@RequiredArgsConstructor
public class UIProductController {

    private final market.product.uiProduct.service.UIProductService UIProductService;

    @GetMapping
    public ResponseEntity<List<UIProductView>> getAll() {
        return new ResponseEntity<>(this.UIProductService.getAllUIProducts(), HttpStatus.OK);
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<List<UIProductView>> getByNameLike(@PathVariable String name) {
        return new ResponseEntity<>(this.UIProductService.getAllUIProductsByNameLike(name), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UIProductView> getById(@PathVariable Long id) {
        return new ResponseEntity<>(this.UIProductService.getUIProduct(id), HttpStatus.OK);
    }

//    @GetMapping("/by-categories/{ids}")
//    public List<UIProduct> getByCategoryIds(@PathVariable Long[] ids) {
//        return this.UIProductService.getAllByCategories(Arrays.asList(ids));
//    }

    @PostMapping
    public ResponseEntity<UIProduct> create(@Validated(Create.class) @RequestBody UIProductDto UIProductDto) {
        return new ResponseEntity<>(this.UIProductService.create(UIProductDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UIProduct> update(@Validated(Update.class) @RequestBody UIProductDto UIProductDto) {
        return new ResponseEntity<>(this.UIProductService.update(UIProductDto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@Valid @RequestBody BulkDeleteDto bulkDeleteDto) {
        this.UIProductService.bulkDelete(bulkDeleteDto.getIds());
        return ResponseEntity.ok("deleted successfully");
    }
}
