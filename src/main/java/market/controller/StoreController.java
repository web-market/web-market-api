package market.controller;

import lombok.RequiredArgsConstructor;
import market.dto.BulkDeleteDto;
import market.dto.store.StoreDto;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.entity.Store;
import market.projection.store.StoreView;
import market.service.StoreService;
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

@RestController
@RequestMapping(path = "/store-management")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/stores")
    public ResponseEntity<List<StoreView>> getAllStores() {
        return new ResponseEntity<>(this.storeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/stores/{id}")
    public ResponseEntity<StoreView> getStore(@PathVariable Long id) {
        return new ResponseEntity<>(this.storeService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/stores")
    public ResponseEntity<Store> create(@Validated(Create.class) @RequestBody StoreDto storeDto) {
        return new ResponseEntity<>(this.storeService.create(storeDto), HttpStatus.CREATED);
    }

    @PutMapping("/stores")
    public ResponseEntity<Store> update(@Validated(Update.class) @RequestBody StoreDto storeDto)   {
        return new ResponseEntity<>(this.storeService.update(storeDto), HttpStatus.OK);
    }

    @DeleteMapping("/stores")
    public ResponseEntity<String> delete(@Valid @RequestBody BulkDeleteDto bulkDeleteDto) {
        this.storeService.bulkDelete(bulkDeleteDto.getIds());
        return ResponseEntity.ok("deleted successfully");
    }

}

