package market.service;

import market.dto.file.image.ImageDto;
import market.dto.media.MediaImageStoreDto;
import market.dto.media.MediaImageUploadDto;
import market.entity.Media;
import market.entity.MediaCategory;
import java.io.IOException;
import java.util.List;

public interface MediaService {

    Media createMedia(MediaCategory folder);

    List<MediaImageStoreDto> store(MediaImageUploadDto mediaImageUploadDto) throws IOException;

    MediaImageStoreDto getPreparedImages(List<ImageDto> images, MediaCategory folder);

    List<MediaImageStoreDto> prepareAllImages(MediaImageUploadDto images) throws IOException;

    void removeImages(Long mediaId) throws IOException;

}
