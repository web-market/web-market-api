package market.filter.controller;

import lombok.RequiredArgsConstructor;
import market.dto.BulkDeleteDto;
import market.filter.dto.FilterDto;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.entity.Filter;
import market.filter.dto.FilterEditView;
import market.filter.dto.FilterView;
import market.filter.service.FilterService;
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
@RequestMapping(path = "/filter-management")
@RequiredArgsConstructor
public class FilterController {

    private final FilterService filterService;

    @GetMapping("/filters")
    public ResponseEntity<List<FilterView>> getAllFilters() {
        return new ResponseEntity<>(this.filterService.getFilters(), HttpStatus.OK);
    }

    @GetMapping("/filter/{id}")
    public ResponseEntity<FilterEditView> getById(@PathVariable Long id) {
        return new ResponseEntity<>(this.filterService.getFilter(id), HttpStatus.OK);
    }

    @PostMapping("/filters")
    public ResponseEntity<Filter> create(@Validated(Create.class) @RequestBody FilterDto filterDto) {
        return new ResponseEntity<>(this.filterService.create(filterDto), HttpStatus.CREATED);
    }

    @PutMapping("/filters")
    public ResponseEntity<Filter> update(@Validated(Update.class) @RequestBody FilterDto filterDto)   {
        return new ResponseEntity<>(this.filterService.update(filterDto), HttpStatus.OK);
    }

    @DeleteMapping("/filters")
    public ResponseEntity<String> delete(@Valid @RequestBody BulkDeleteDto bulkDeleteDto) {
        this.filterService.deleteFilters(bulkDeleteDto.getIds());
        return ResponseEntity.ok("deleted successfully");
    }
}