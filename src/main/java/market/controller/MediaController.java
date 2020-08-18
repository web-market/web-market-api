package market.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import market.dto.media.ImageGroupUploadDto;
import market.projection.media.MediaView;
import market.service.MediaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/media-management")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @GetMapping("/media/media-folder/{id}")
    public ResponseEntity<List<MediaView>> getImagesByMediaFolderId(@PathVariable Long id) {
        return new ResponseEntity<>(this.mediaService.getMediaByFolder(id), HttpStatus.OK);
    }

    @GetMapping("/media/{id}")
    public ResponseEntity<MediaView> getImagesByMediaId(@PathVariable Long id) {
        return new ResponseEntity<>(this.mediaService.getMedia(id), HttpStatus.OK);
    }

    //TODO: refactor method to handle such exceptions properly
    @SneakyThrows(IOException.class)
    @PostMapping(value = "/media", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> upload(@ModelAttribute ImageGroupUploadDto imageGroupUploadDto) {
        this.mediaService.storeImages(imageGroupUploadDto);
        return ResponseEntity.ok("media with images uploaded successfully");

    }

    @DeleteMapping("/media/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws IOException { 
        this.mediaService.deleteFiles(id);
        return ResponseEntity.ok("media with images deleted successfully");
    }

}
