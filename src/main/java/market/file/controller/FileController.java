package market.file.controller;

import lombok.RequiredArgsConstructor;
import market.file.service.FileService;
import market.media.service.MediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        this.fileService.removeFilesFromFolder(id);
        this.fileService.deleteFilesByMedia(id);
        return ResponseEntity.ok("media with images deleted successfully");
    }
}
