package market.controller;

import lombok.RequiredArgsConstructor;
import market.dto.BulkDeleteDto;
import market.dto.filterValue.FilterValueDto;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.entity.FilterValue;
import market.projection.filterValue.FilterValueView;
import market.service.FilterValueService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/filter-value-management")
@RequiredArgsConstructor
public class FilterValueController {

    private final FilterValueService filterValueService;

    @GetMapping("/filter-values")
    public List<FilterValueView> getAllFilterValues() {
        return this.filterValueService.getFilterValues();
    }

    @GetMapping("/filter-values/filter/{id}")
    public List<FilterValueView> getAllByFilterId(@PathVariable Long id) {
        return this.filterValueService.getAllByFilter(id);
    }

    @GetMapping("/filter-value/{id}")
    public FilterValueView getById(@PathVariable Long id) {
        return this.filterValueService.getFilterValue(id);
    }

    @PostMapping("/filter-values")
    public FilterValue create(@Validated(Create.class) @RequestBody FilterValueDto filterValueDto) {
        return this.filterValueService.create(filterValueDto);
    }

    @PutMapping("/filter-values")
    public FilterValue update(@Validated(Update.class) @RequestBody FilterValueDto filterValueDto) {
        return this.filterValueService.update(filterValueDto);
    }

    @DeleteMapping("/filter-values")
    public ResponseEntity<String> delete(@Valid @RequestBody BulkDeleteDto bulkDeleteDto) {
        this.filterValueService.bulkDelete(bulkDeleteDto.getIds());
        return ResponseEntity.ok("deleted successfully");
    }
}