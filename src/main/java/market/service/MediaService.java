package market.service;

import market.dto.media.ImageGroupUploadDto;
import market.entity.Media;
import market.projection.media.MediaView;

import java.io.IOException;
import java.util.List;

public interface MediaService {

    //Projections
    List<MediaView> getMediaByFolder(Long mediaFolderId);

    MediaView getMedia(Long id);

    //DTOs
    Media create(Long mediaFolderId);

    void storeImages(ImageGroupUploadDto imageGroupUploadDto) throws IOException;

    void deleteFiles(Long mediaId) throws IOException;

}
