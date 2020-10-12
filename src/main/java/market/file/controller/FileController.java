package market.file.controller;

import lombok.RequiredArgsConstructor;
import market.dto.BulkDeleteDto;
import market.file.service.FileService;
import market.media.service.MediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(path = "/file-management")
@RequiredArgsConstructor
public class FileController {

    private final MediaService mediaService;
    private final FileService fileService;

    @DeleteMapping("/files/media/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws IOException {
        this.mediaService.delete(id);
        this.fileService.removeFileGroupFromFolder(id);
        this.fileService.deleteFilesByMedia(id);
        return ResponseEntity.ok("Media with images deleted successfully");
    }

    @DeleteMapping("/files/media")
    public ResponseEntity<String> deleteMany(@Valid @RequestBody BulkDeleteDto bulkDeleteDto) throws IOException {
        this.mediaService.deleteMany(bulkDeleteDto.getIds());
        this.fileService.removeFileGroupsFromFolder(bulkDeleteDto.getIds());
        this.fileService.deleteFilesInManyMedia(bulkDeleteDto.getIds());
        return ResponseEntity.ok("Group of medias with images deleted successfully");
    }
}
