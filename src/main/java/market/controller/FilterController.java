package market.controller;

import lombok.RequiredArgsConstructor;
import market.dto.BulkDeleteDto;
import market.dto.filter.FilterDto;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.entity.Filter;
import market.projection.filter.FilterEditView;
import market.projection.filter.FilterView;
import market.service.FilterService;
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
    public List<FilterView> getAllFilters() {
        return this.filterService.getFilters();
    }

    @GetMapping("/filter/{id}")
    public FilterEditView getById(@PathVariable Long id) {
        return this.filterService.getFilter(id);
    }

    @PostMapping("/filters")
    public Filter create(@Validated(Create.class) @RequestBody FilterDto filterDto) {
        return this.filterService.create(filterDto);
    }

    @PutMapping("/filters")
    public Filter update(@Validated(Update.class) @RequestBody FilterDto filterDto)   {
        return this.filterService.update(filterDto);
    }

    @DeleteMapping("/filters")
    public ResponseEntity<String> delete(@Valid @RequestBody BulkDeleteDto bulkDeleteDto) {
        this.filterService.deleteFilters(bulkDeleteDto.getIds());
        return ResponseEntity.ok("deleted successfully");
    }
}