package market.controller;

import lombok.RequiredArgsConstructor;
import market.dto.BulkDeleteDto;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.dto.uiProduct.UIProductDto;
import market.entity.UIProduct;
import market.service.UIProductService;
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

@RestController
@RequestMapping(path = "/products")
@RequiredArgsConstructor
public class UIProductController {

    private final UIProductService UIProductService;

    @GetMapping
    public List<UIProduct> getAll() {
        return this.UIProductService.getAll();
    }

    @GetMapping("/{id}")
    public UIProduct getById(@PathVariable Long id) {
        return this.UIProductService.findOneById(id);
    }

//    @GetMapping("/by-categories/{ids}")
//    public List<UIProduct> getByCategoryIds(@PathVariable Long[] ids) {
//        return this.UIProductService.getAllByCategories(Arrays.asList(ids));
//    }
//
//    @GetMapping("/by-filter-values/{ids}")
//    public List<UIProduct> getByFilerValueIds(@PathVariable Long[] ids) {
//        return this.UIProductService.getAllByFilterValues(Arrays.asList(ids));
//    }

    @GetMapping("/by-name/{name}")
    public List<UIProduct> getByNameLike(@PathVariable String name) {
        return this.UIProductService.getAllByNameLike(name);
    }

    @PostMapping
    public UIProduct create(@Validated(Create.class) @RequestBody UIProductDto UIProductDto) {
        return this.UIProductService.create(UIProductDto);
    }

    @PutMapping
    public UIProduct update(@Validated(Update.class) @RequestBody UIProductDto UIProductDto) {
        return this.UIProductService.update(UIProductDto);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@Valid @RequestBody BulkDeleteDto bulkDeleteDto) {
        this.UIProductService.bulkDelete(bulkDeleteDto.getIds());
        return ResponseEntity.ok("deleted successfully");
    }
}
