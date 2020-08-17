package market.service;

import market.entity.File;
import market.entity.Media;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    File saveResized(MultipartFile image, Media media, int width, int height);

}
