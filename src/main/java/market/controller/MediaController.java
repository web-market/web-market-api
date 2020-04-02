package market.controller;

import lombok.RequiredArgsConstructor;
import market.dto.media.MediaDto;
import market.dto.media.MediaUploadDto;
import market.service.MediaService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @PostMapping(value = "/media", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public List<MediaDto> createProduct(@ModelAttribute MediaUploadDto mediaUploadDto) throws IOException {
        List<MediaDto> uploadResult = this.mediaService.uploadMedia(mediaUploadDto);

        return uploadResult;
    }


}
