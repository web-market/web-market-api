package market.fileProcessing.imageResizing;

import market.entity.Media;
import market.file.image.dto.ImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

public interface ImageProcessingService {

    Map<ImageDto, BufferedImage> processImage(MultipartFile image, Media media) throws IOException;

}
