package market.provider.controller;

import lombok.RequiredArgsConstructor;
import market.dto.BulkDeleteDto;
import market.provider.dto.ProviderDto;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.entity.Provider;
import market.provider.dto.ProviderItemView;
import market.provider.service.ProviderService;
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
@RequestMapping(path = "/provider-management")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;

    @GetMapping("/providers")
    public ResponseEntity<List<ProviderItemView>> getAll() {
        return new ResponseEntity<>(this.providerService.getProviders(), HttpStatus.OK);
    }

    @GetMapping("/providers/{id}")
    public ResponseEntity<ProviderItemView> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(this.providerService.getProvider(id), HttpStatus.OK);
    }

    @PostMapping("/providers")
    public ResponseEntity<Provider> create(@Validated(Create.class) @RequestBody ProviderDto providerDto) {
        return new ResponseEntity<>(this.providerService.create(providerDto), HttpStatus.CREATED);
    }

    @PutMapping("/providers")
    public ResponseEntity<Provider> update(@Validated(Update.class) @RequestBody ProviderDto providerDto)   {
        return new ResponseEntity<>(this.providerService.update(providerDto), HttpStatus.OK);
    }

    @DeleteMapping("/providers")
    public ResponseEntity<String> delete(@Valid @RequestBody BulkDeleteDto bulkDeleteDto) {
        this.providerService.bulkDelete(bulkDeleteDto.getIds());
        return ResponseEntity.ok("deleted successfully");
    }

}
