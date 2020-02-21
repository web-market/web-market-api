package market.controller;

import lombok.RequiredArgsConstructor;
import market.dto.filterValue.FilterValueDto;
import market.entity.FilterValue;
import market.service.FilterValueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/filterValues")
@RequiredArgsConstructor
public class FilterValueController {

    private final FilterValueService filterValueService;

    @GetMapping("/all")
    public List<FilterValue> getAll() {
        return this.filterValueService.getAll();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.filterValueService.delete(id);
        return ResponseEntity.ok("deleted successfully");
    }

    @PostMapping
    public FilterValue create(@RequestBody FilterValueDto filterValueDto) {
        return this.filterValueService.create(filterValueDto);
    }

    @PutMapping
    public FilterValue update(@RequestBody FilterValueDto filterValueDto) {
        return this.filterValueService.update(filterValueDto);
    }
}
