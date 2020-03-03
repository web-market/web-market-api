package market.controller;

import lombok.RequiredArgsConstructor;
import market.dto.BulkDeleteDto;
import market.dto.filter.FilterDto;
import market.entity.Filter;
import market.projection.filter.FilterView;
import market.service.FilterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/filters")
@RequiredArgsConstructor
public class FilterController {

    private final FilterService filterService;

    @GetMapping
    public List<FilterView> getAll() {
        return this.filterService.getAll();
    }

    @PostMapping
    public Filter create(@RequestBody FilterDto filterDto) {
        return this.filterService.create(filterDto);
    }

    @PutMapping
    public Filter update(@RequestBody FilterDto filterDto)   {
        return this.filterService.update(filterDto);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody BulkDeleteDto bulkDeleteDto) {
        this.filterService.bulkDelete(bulkDeleteDto.getIds());
        return ResponseEntity.ok("deleted successfully");
    }
}