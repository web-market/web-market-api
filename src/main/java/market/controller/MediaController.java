package market.controller;

import lombok.RequiredArgsConstructor;
import market.dto.media.MediaImageUploadDto;
import market.entity.Media;
import market.service.MediaService;
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

    @GetMapping("/media/folder/{id}")
    public List<Media> getFilesByFolderId(@PathVariable Long id) {
        return null;
    }

    @PostMapping(value = "/media", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void upload(@ModelAttribute MediaImageUploadDto mediaImageUploadDto) throws IOException {
        this.mediaService.store(mediaImageUploadDto);
    }


    @DeleteMapping("/media/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws IOException {
        this.mediaService.removeImages(id);
        return ResponseEntity.ok("media with images deleted successfully");
    }

}
