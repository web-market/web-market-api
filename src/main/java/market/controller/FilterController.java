package market.controller;

import lombok.RequiredArgsConstructor;
import market.dto.filter.FilterDto;
import market.entity.Filter;
import market.service.FilterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/filters")
@RequiredArgsConstructor
public class FilterController {

    private final FilterService filterService;

    @GetMapping("/all")
    public List<Filter> getAll() {
        return this.filterService.getAll();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.filterService.delete(id);
        return ResponseEntity.ok("deleted successfully");
    }

    @PostMapping
    public Filter create(@RequestBody FilterDto filterDto) {
        return this.filterService.create(filterDto);
    }

    @PutMapping
    public Filter update(@RequestBody FilterDto filterDto)   {
        return this.filterService.update(filterDto);
    }
}
