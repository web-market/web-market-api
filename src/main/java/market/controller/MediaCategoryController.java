package market.controller;

import lombok.RequiredArgsConstructor;
import market.dto.mediaCategory.MediaCategoryDto;
import market.entity.MediaCategory;
import market.projection.mediaCategory.MediaCategoryInlineView;
import market.service.MediaCategoryService;
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

@RestController
@RequestMapping(path = "/media-category-management")
@RequiredArgsConstructor
public class MediaCategoryController {

    private final MediaCategoryService mediaCategoryService;

    @GetMapping("/media-category-inline")
    public List<MediaCategoryInlineView> getAllMediaCategories() {
        return this.mediaCategoryService.getAllInline();
    }

    @PostMapping("/media-category")
    public MediaCategory create(@RequestBody MediaCategoryDto mediaCategoryDto) {
        return this.mediaCategoryService.create(mediaCategoryDto);
    }

    @PutMapping("/media-category")
    public MediaCategory update(@RequestBody MediaCategoryDto mediaCategoryDto) {
        return this.mediaCategoryService.update(mediaCategoryDto);
    }

    @DeleteMapping("/media-category/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.mediaCategoryService.delete(id);
        return ResponseEntity.ok("media category deleted successfully");
    }

}
