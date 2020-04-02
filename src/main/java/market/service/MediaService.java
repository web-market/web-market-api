package market.service;

import market.dto.media.MediaDto;
import market.dto.media.MediaUploadDto;

import java.io.IOException;
import java.util.List;

public interface MediaService {

    List<MediaDto> uploadMedia(MediaUploadDto mediaUploadDto) throws IOException;

}
