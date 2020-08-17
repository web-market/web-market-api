package market.controller;

import lombok.RequiredArgsConstructor;
import market.dto.mediaFolder.MediaFolderDto;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.entity.MediaFolder;
import market.projection.mediaFolder.MediaFolderDropdownView;
import market.projection.mediaFolder.MediaFolderEditView;
import market.projection.mediaFolder.MediaFolderInlineView;
import market.service.MediaFolderService;
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
@RequestMapping(path = "/media-folder-management")
@RequiredArgsConstructor
public class MediaFolderController {

    private final MediaFolderService mediaFolderService;

    @GetMapping("/media-folder-inline")
    public List<MediaFolderInlineView> getAllMediaCategories() {
        return this.mediaFolderService.getFoldersInline();
    }

    @GetMapping("/media-folder/all")
    public List<MediaFolderDropdownView> getAllForDropdown() {
        return this.mediaFolderService.getFolders();
    }

    @GetMapping("/available-folders-to-move-in/{id}")
    public List<MediaFolderDropdownView> getAvailableParents(@PathVariable Long id) {
        return this.mediaFolderService.getAllowedParents(id);
    }

    @GetMapping("/media-folder/{id}")
    public MediaFolderEditView getSingleCategory(@PathVariable Long id) {
        return this.mediaFolderService.getFolderToEdit(id);
    }

    @PostMapping("/media-folder")
    public MediaFolder create(@Validated(Create.class) @RequestBody MediaFolderDto mediaFolderDto) {
        return this.mediaFolderService.create(mediaFolderDto);
    }

    @PutMapping("/media-folder")
    public MediaFolder update(@Validated(Update.class) @RequestBody MediaFolderDto mediaFolderDto) {
        return this.mediaFolderService.update(mediaFolderDto);
    }

    @DeleteMapping("/media-folder/{id}")
    public ResponseEntity<String> delete(@Valid @PathVariable Long id) {
        this.mediaFolderService.delete(id);
        return ResponseEntity.ok("media category deleted successfully");
    }

}
