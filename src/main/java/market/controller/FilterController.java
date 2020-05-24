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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/filters")
@RequiredArgsConstructor
public class FilterController {

    private final FilterService filterService;

    @GetMapping
    public List<FilterView> getAllFilters() {
        return this.filterService.getAll();
    }

    @GetMapping("/{id}")
    public FilterEditView getById(@PathVariable Long id) {
        return this.filterService.findOneById(id);
    }

    @PostMapping
    public Filter create(@Validated(Create.class) @RequestBody FilterDto filterDto) {
        return this.filterService.create(filterDto);
    }

    @PutMapping
    public Filter update(@Validated(Update.class) @RequestBody FilterDto filterDto)   {
        return this.filterService.update(filterDto);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@Valid @RequestBody BulkDeleteDto bulkDeleteDto) {
        this.filterService.bulkDelete(bulkDeleteDto.getIds());
        return ResponseEntity.ok("deleted successfully");
    }
}