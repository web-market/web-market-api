package market.file.service;

import market.entity.File;
import market.entity.Media;
import market.file.image.dto.ImageDto;

import java.io.IOException;
import java.util.List;

public interface FileService {

    //For image
    File save(ImageDto image, Media media);

    void removeFileGroupFromFolder(Long mediaId) throws IOException;

    void deleteFilesByMedia(Long mediaId);

    void removeFileGroupsFromFolder(List<Long> mediaIds) throws IOException;

    void deleteFilesInManyMedia(List<Long> ids);

}
