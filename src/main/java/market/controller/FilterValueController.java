package market.controller;

import lombok.RequiredArgsConstructor;
import market.dto.BulkDeleteDto;
import market.dto.filterValue.FilterValueDto;
import market.entity.FilterValue;
import market.service.FilterValueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/filter-values")
@RequiredArgsConstructor
public class FilterValueController {

    private final FilterValueService filterValueService;

    @GetMapping
    public List<FilterValue> getAll() {
        return this.filterValueService.getAll();
    }

    @GetMapping(path = "/{id}")
    public List<FilterValue> getAllByFilterId(@PathVariable Long id) {
        return this.filterValueService.getAllByFilterId(id);
    }

    @PostMapping
    public FilterValue create(@RequestBody FilterValueDto filterValueDto) {
        return this.filterValueService.create(filterValueDto);
    }

    @PutMapping
    public FilterValue update(@RequestBody FilterValueDto filterValueDto) {
        return this.filterValueService.update(filterValueDto);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody BulkDeleteDto bulkDeleteDto) {
        this.filterValueService.bulkDelete(bulkDeleteDto.getIds());
        return ResponseEntity.ok("deleted successfully");
    }
}