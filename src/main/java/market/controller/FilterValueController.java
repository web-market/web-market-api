package market.controller;

import lombok.RequiredArgsConstructor;
import market.dto.BulkDeleteDto;
import market.dto.filterValue.FilterValueDto;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.entity.FilterValue;
import market.service.FilterValueService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/filter-values")
@RequiredArgsConstructor
public class FilterValueController {

    private final FilterValueService filterValueService;

    @GetMapping
    public List<FilterValue> getAllFilterValues() {
        return this.filterValueService.getAll();
    }

    @GetMapping("/{id}")
    public FilterValue getById(@PathVariable Long id) {
        return this.filterValueService.findOneById(id);
    }

    @GetMapping("/filter/{id}")
    public List<FilterValue> getAllByFilterId(@PathVariable Long id) {
        return this.filterValueService.getAllByFilterId(id);
    }

    @PostMapping
    public FilterValue create(@Validated(Create.class) @RequestBody FilterValueDto filterValueDto) {
        return this.filterValueService.create(filterValueDto);
    }

    @PutMapping
    public FilterValue update(@Validated(Update.class) @RequestBody FilterValueDto filterValueDto) {
        return this.filterValueService.update(filterValueDto);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@Valid @RequestBody BulkDeleteDto bulkDeleteDto) {
        this.filterValueService.bulkDelete(bulkDeleteDto.getIds());
        return ResponseEntity.ok("deleted successfully");
    }
}