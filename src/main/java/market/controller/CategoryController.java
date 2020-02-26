package market.controller;

import lombok.RequiredArgsConstructor;
import market.dto.category.CategoryDropDto;
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
@RequestMapping("/category-management")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/categories/fill-dropdown")
    public List<CategoryDropdownView> getAll() {
        return this.categoryService.getAll();
    }

    @GetMapping("/root-categories")
    public List<CategoryItemView> getAllRootCategories() {
        return this.categoryService.getAllRootCategories();
    }

    @GetMapping("/root-categories/{id}")
    public List<CategoryItemView> getAllChildrenByParentId(@PathVariable Long id) {
        return this.categoryService.getAllByParentCategoryId(id);
    }

    @GetMapping("/available-parent-categories/{id}")
    public List<CategoryDropdownView> getAvailableParents(@PathVariable Long id) {
        return this.categoryService.getAvailableParentCategories(id);
    }

    @GetMapping("/categories/{id}")
    public CategoryEditView getSingleCategory(@PathVariable Long id) {
        return this.categoryService.getById(id);
    }

    @PostMapping("/categories")
    public Category create(@RequestBody CategoryDto categoryDto) {
        return this.categoryService.create(categoryDto);
    }

    @PutMapping("/categories")
    public Category update(@RequestBody CategoryDto categoryDto)   {
        return this.categoryService.update(categoryDto);
    }

    @DeleteMapping("/categories")
    public ResponseEntity<String> delete(@RequestBody CategoryDropDto categoryDropDto) {
        this.categoryService.delete(categoryDropDto.getId());
        return ResponseEntity.ok("deleted successfully");
    }

}
