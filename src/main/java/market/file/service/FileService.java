package market.file.service;

import market.entity.File;
import market.entity.Media;
import market.file.image.dto.ImageDto;

import java.io.IOException;

public interface FileService {

    //For image
    File save(ImageDto image, Media media);

    void removeFilesFromFolder(Long mediaId) throws IOException;

    void deleteFilesByMedia(Long mediaId);

}
