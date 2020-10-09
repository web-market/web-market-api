package market.media.service;

import market.entity.Media;
import market.media.dto.MediaImageGroup;

import java.util.List;

public interface MediaService {

    //Projections
    List<MediaImageGroup> getAllMediaInFolder(Long mediaFolderId);

    MediaImageGroup getMedia(Long id);

    //DTOs
    Media create(Long mediaFolderId);

    void delete(Long mediaId);

}
