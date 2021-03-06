package market.controller;

import lombok.RequiredArgsConstructor;
import market.dto.category.CategoryDto;
import market.entity.Category;
import market.projection.category.CategoryDropdownView;
import market.projection.category.CategoryEditView;
import market.projection.category.CategoryItemView;
import market.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//*TODO: parent_category_id not null but zero
// and related categories*/

@RestController
@RequestMapping(path = "/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/all")
    public List<CategoryDropdownView> getAll() {
        return this.categoryService.getAll();
    }

    @GetMapping(value = {"", "/{id}"})
    public List<CategoryItemView> getAllByParentCategoryId(@PathVariable(required = false, value = "id") Long id) {
        return this.categoryService.getAllByParentCategoryId(id);
    }

    @GetMapping("/category/{id}")
    public CategoryEditView getSingleCategory(@PathVariable Long id) {
        return this.categoryService.getById(id);
    }

    @PostMapping
    public Category create(@RequestBody CategoryDto categoryDto) {
        return this.categoryService.create(categoryDto);
    }

    @PutMapping
    public Category update(@RequestBody CategoryDto categoryDto)   {
        return this.categoryService.update(categoryDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.categoryService.delete(id);
        return ResponseEntity.ok("deleted successfully");
    }

}
