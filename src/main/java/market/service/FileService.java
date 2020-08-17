package market.service;

import market.dto.file.image.ImageDto;
import market.entity.File;
import market.entity.Media;
import market.projection.file.ImageFileView;

import java.util.List;

public interface FileService {

    List<ImageFileView> getByMediaId(Long mediaId);

    File saveImage(ImageDto image, Media media);

    void deleteByMediaId(Long id);
}
