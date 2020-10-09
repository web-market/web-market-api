package market.manufacturer.controller;

import lombok.RequiredArgsConstructor;
import market.dto.BulkDeleteDto;
import market.manufacturer.dto.ManufacturerDto;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.entity.Manufacturer;
import market.manufacturer.dto.ManufacturerView;
import market.manufacturer.service.ManufacturerService;
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
@RequestMapping(path = "/manufacturer-management")
@RequiredArgsConstructor
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    @GetMapping("/manufacturers")
    public ResponseEntity<List<ManufacturerView>> getAll() {
        return new ResponseEntity<>(this.manufacturerService.getManufacturers(), HttpStatus.OK);
    }

    @GetMapping("/manufacturers/{id}")
    public ResponseEntity<ManufacturerView> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(this.manufacturerService.getManufacturer(id), HttpStatus.OK);
    }

    @PostMapping("/manufacturers")
    public ResponseEntity<Manufacturer> create(@Validated(Create.class) @RequestBody ManufacturerDto manufacturerDto) {
        return new ResponseEntity<>(this.manufacturerService.create(manufacturerDto), HttpStatus.CREATED);
    }

    @PutMapping("/manufacturers")
    public ResponseEntity<Manufacturer> update(@Validated(Update.class) @RequestBody ManufacturerDto manufacturerDto)   {
        return new ResponseEntity<>(this.manufacturerService.update(manufacturerDto), HttpStatus.OK);
    }

    @DeleteMapping("/manufacturers")
    public ResponseEntity<String> delete(@Valid @RequestBody BulkDeleteDto bulkDeleteDto) {
        this.manufacturerService.bulkDelete(bulkDeleteDto.getIds());
        return ResponseEntity.ok("deleted successfully");
    }

}
