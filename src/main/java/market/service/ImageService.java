package market.service;

import market.dto.file.image.ImageDto;
import market.entity.File;
import market.entity.Media;
import market.projection.file.ImageFileView;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    //Projections
    List<ImageFileView> getImagesByMedia(Long mediaId);

    //DTOs
    File saveImage(ImageDto image, Media media);

    File storeResizedImage(MultipartFile image, Media media, int width, int height);

}
