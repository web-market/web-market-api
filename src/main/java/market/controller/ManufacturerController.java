package market.controller;

import lombok.RequiredArgsConstructor;
import market.dto.BulkDeleteDto;
import market.dto.manufacturer.ManufacturerDto;
import market.entity.Manufacturer;
import market.projection.manufacturer.ManufacturerView;
import market.service.ManufacturerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/manufacturer-management")
@RequiredArgsConstructor
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    @GetMapping("/manufacturers")
    public List<ManufacturerView> getAll() {
        return this.manufacturerService.getAll();
    }

    @GetMapping("/manufacturers/{id}")
    public ManufacturerView getOne(@PathVariable Long id) {
        return this.manufacturerService.getOne(id);
    }

    @PostMapping("/manufacturers")
    public Manufacturer create(@RequestBody ManufacturerDto manufacturerDto) {
        return this.manufacturerService.create(manufacturerDto);
    }

    @PutMapping("/manufacturers")
    public Manufacturer update(@RequestBody ManufacturerDto manufacturerDto)   {
        return this.manufacturerService.update(manufacturerDto);
    }

    @DeleteMapping("/manufacturers")
    public ResponseEntity<String> delete(@RequestBody BulkDeleteDto bulkDeleteDto) {
        this.manufacturerService.bulkDelete(bulkDeleteDto.getIds());
        return ResponseEntity.ok("deleted successfully");
    }

}
