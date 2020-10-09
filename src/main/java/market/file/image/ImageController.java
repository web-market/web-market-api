package market.file.image;

import lombok.RequiredArgsConstructor;
import market.file.image.dto.ImagesUploadDto;
import market.file.image.service.ImageService;
import market.media.dto.MediaImageGroup;
import market.media.service.MediaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/image-management")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final MediaService mediaService;

    @GetMapping("/images/media-folder/{id}")
    public ResponseEntity<List<MediaImageGroup>> getImagesInMediaFolder(@PathVariable Long id) {
        return new ResponseEntity<>(this.mediaService.getAllMediaInFolder(id), HttpStatus.OK);
    }

    @GetMapping("/images/media/{id}")
    public ResponseEntity<MediaImageGroup> getImageMediaGroup(@PathVariable Long id) {
        return new ResponseEntity<>(this.mediaService.getMedia(id), HttpStatus.OK);
    }

    @PostMapping(value = "/images/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> upload(@ModelAttribute ImagesUploadDto imagesUploadDto) throws IOException {
        this.imageService.storeImages(imagesUploadDto);
        return ResponseEntity.ok("media with images uploaded successfully");
    }

}
