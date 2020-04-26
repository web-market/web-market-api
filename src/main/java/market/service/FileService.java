package market.service;

import market.dto.file.image.ImageDto;
import market.entity.File;
import market.entity.Media;

public interface FileService {

    File save(ImageDto image, String pathToImage, Media media);

    void deleteByMediaId(Long id);
}
