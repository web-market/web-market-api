package market.controller;

import lombok.RequiredArgsConstructor;
import market.dto.BulkDeleteDto;
import market.dto.filterValue.FilterValueDto;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.entity.FilterValue;
import market.projection.filterValue.FilterValueView;
import market.service.FilterValueService;
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
@RequestMapping(path = "/filter-value-management")
@RequiredArgsConstructor
public class FilterValueController {

    private final FilterValueService filterValueService;

    @GetMapping("/filter-values")
    public ResponseEntity<List<FilterValueView>> getAllFilterValues() {
        return new ResponseEntity<>(this.filterValueService.getFilterValues(), HttpStatus.OK);
    }

    @GetMapping("/filter-values/filter/{id}")
    public ResponseEntity<List<FilterValueView>> getAllByFilterId(@PathVariable Long id) {
        return new ResponseEntity<>(this.filterValueService.getAllByFilter(id), HttpStatus.OK);
    }

    @GetMapping("/filter-value/{id}")
    public ResponseEntity<FilterValueView> getById(@PathVariable Long id) {
        return new ResponseEntity<>(this.filterValueService.getFilterValue(id), HttpStatus.OK);
    }

    @PostMapping("/filter-values")
    public ResponseEntity<FilterValue> create(@Validated(Create.class) @RequestBody FilterValueDto filterValueDto) {
        return new ResponseEntity<>(this.filterValueService.create(filterValueDto), HttpStatus.CREATED);
    }

    @PutMapping("/filter-values")
    public ResponseEntity<FilterValue> update(@Validated(Update.class) @RequestBody FilterValueDto filterValueDto) {
        return new ResponseEntity<>(this.filterValueService.update(filterValueDto), HttpStatus.OK);
    }

    @DeleteMapping("/filter-values")
    public ResponseEntity<String> delete(@Valid @RequestBody BulkDeleteDto bulkDeleteDto) {
        this.filterValueService.bulkDelete(bulkDeleteDto.getIds());
        return ResponseEntity.ok("deleted successfully");
    }
}