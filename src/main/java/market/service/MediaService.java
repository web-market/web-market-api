package market.service;

import market.dto.media.ImageGroupUploadDto;
import market.entity.Media;
import market.projection.media.MediaView;

import java.io.IOException;
import java.util.List;

public interface MediaService {

    List<MediaView> getByMediaFolderId(Long id);

    MediaView getById(Long id);

    Media createMedia(Long mediaFolderId);

    void store(ImageGroupUploadDto imageGroupUploadDto) throws IOException;

    void removeImages(Long mediaId) throws IOException;

}
