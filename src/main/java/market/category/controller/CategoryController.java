package market.category.controller;

import lombok.RequiredArgsConstructor;
import market.category.dto.CategoryDropDto;
import market.category.dto.CategoryDto;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.entity.Category;
import market.category.dto.CategoryDropdownView;
import market.category.dto.CategoryEditView;
import market.category.dto.CategoryItemView;
import market.category.service.CategoryService;
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
@RequestMapping("/category-management")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/categories/fill-dropdown")
    public ResponseEntity<List<CategoryDropdownView>> getAllCategories() {
        return new ResponseEntity<>(this.categoryService.getCategories(), HttpStatus.OK);
    }

    @GetMapping("/root-categories")
    public ResponseEntity<List<CategoryItemView>> getHighLevelCategories() {
        return new ResponseEntity<>(this.categoryService.getFirstLevelCategories(), HttpStatus.OK);
    }

    @GetMapping("/root-categories/{id}")
    public ResponseEntity<List<CategoryItemView>> getChildrenByParentId(@PathVariable Long id) {
        return new ResponseEntity<>(this.categoryService.getAllByParent(id), HttpStatus.OK);
    }

    @GetMapping("/available-parent-categories/{id}")
    public ResponseEntity<List<CategoryDropdownView>> getAvailableParents(@PathVariable Long id) {
        return new ResponseEntity<>(this.categoryService.getAllowedParents(id), HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryEditView> getSingleCategory(@PathVariable Long id) {
        return new ResponseEntity<>(this.categoryService.getCategory(id), HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> create(@Validated(Create.class) @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(this.categoryService.create(categoryDto), HttpStatus.CREATED);
    }

    @PutMapping("/categories")
    public ResponseEntity<Category> update(@Validated(Update.class) @RequestBody CategoryDto categoryDto)   {
        return new ResponseEntity<>(this.categoryService.update(categoryDto), HttpStatus.OK);
    }

    @DeleteMapping("/categories")
    public ResponseEntity<String> delete(@Valid @RequestBody CategoryDropDto categoryDropDto) {
        this.categoryService.delete(categoryDropDto.getId(), categoryDropDto.getDeleteSubCategories());
        return ResponseEntity.ok("deleted successfully");
    }
}