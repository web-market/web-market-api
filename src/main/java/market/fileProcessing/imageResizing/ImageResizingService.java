package market.fileProcessing.imageResizing;

import lombok.RequiredArgsConstructor;
import market.entity.Media;
import market.file.image.dto.ImageDto;
import market.fileProcessing.imageResizing.imageStaticFactory.ImageStaticFactory;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageResizingService implements ImageProcessingService {

    private final MultipartProperties fileUploadProperties;

    @Override
    public Map<ImageDto, BufferedImage> processImage(MultipartFile image, Media media) throws IOException {
        var location = fileUploadProperties.getLocation();
        var resizedGroupOfImages = new HashMap<ImageDto, BufferedImage>();
        for (ImageSize resolution : ImageSize.values()) {
            var resizedImage = ImageStaticFactory.createResizedImage(image, resolution.getWidth(), resolution.getHeight());
            var imageAsFile = ImageStaticFactory.createImageAsFile(image, location, media.getId(), resolution.getWidth(), resolution.getHeight());
            resizedGroupOfImages.put(imageAsFile, resizedImage);
        }
        return resizedGroupOfImages;
    }

}
