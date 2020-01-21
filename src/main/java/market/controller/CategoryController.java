package market.controller;

import lombok.RequiredArgsConstructor;
import market.dto.category.CategoryDropdownDto;
import market.dto.category.CategoryDto;
import market.dto.category.CreateCategoryDto;
import market.entity.Category;
import market.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/all")
    public List<CategoryDropdownDto> getAll() {
        return this.categoryService.getAll();
    }

    @GetMapping(value = {"", "/{id}"})
    public List<CategoryDto> getAllByParentCategoryId(@PathVariable(required = false, value = "id") Long id) {
        return this.categoryService.getAllByParentCategoryId(id);
    }

    @PostMapping
    public Category create(@RequestBody CreateCategoryDto categoryDto) {
        return this.categoryService.create(categoryDto);
    }

    @PutMapping
    public Category update(@RequestBody CategoryDto categoryDto) {
        return this.categoryService.update(categoryDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.categoryService.deleteCategory(id);
        return ResponseEntity.ok("deleted successfully");
    }

}
