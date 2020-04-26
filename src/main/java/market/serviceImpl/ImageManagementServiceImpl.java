package market.serviceImpl;

import javaxt.io.Image;
import lombok.RequiredArgsConstructor;
import market.dto.file.image.ImageDto;
import market.entity.ImageSize;
import market.entity.ResolutionType;
import market.service.ImageManagementService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageManagementServiceImpl implements ImageManagementService {

    @Override
    public List<ImageDto> getInMentionedResolutions(MultipartFile originalImage, Boolean lowResolution,
                                                    Boolean mediumResolution, Boolean highResolution) throws IOException {
        List<ImageDto> images = new ArrayList<>();
        String originalImageName = originalImage.getOriginalFilename();
        Image originalResolutionImage = new Image(originalImage.getBytes());

        images.add(this.prepareImageToStore(originalResolutionImage, originalImageName));
        if (lowResolution) {
            Image lowResolutionImage = this.changeImageResolution(originalImage, ResolutionType.LOW);
            images.add(this.prepareImageToStore(lowResolutionImage, originalImageName));
        }
        if (mediumResolution) {
            Image mediumResolutionImage = this.changeImageResolution(originalImage, ResolutionType.MEDIUM);
            images.add(this.prepareImageToStore(mediumResolutionImage, originalImageName));
        }
        if (highResolution) {
            Image highResolutionImage = this.changeImageResolution(originalImage, ResolutionType.HIGH);
            images.add(this.prepareImageToStore(highResolutionImage, originalImageName));
        }

        return images;
    }

    public ImageDto prepareImageToStore(Image changedImage, String originalName) {
        ImageDto imageToStore = new ImageDto();
        imageToStore.setName(this.createImageName(originalName, changedImage.getWidth(), changedImage.getHeight()));
        imageToStore.setExtension(FilenameUtils.getExtension(originalName));
        imageToStore.setSize(String.valueOf(changedImage.getExifTags().size()));
        imageToStore.setImage(changedImage);
        return imageToStore;
    }

    @Override
    public String createImageName(String originalName, int width, int height) {
        String baseNameWithoutExtension = FilenameUtils.getBaseName(originalName);
        String extension = FilenameUtils.getExtension(originalName);
        return new StringBuilder()
                .append(baseNameWithoutExtension)
                .append(width)
                .append("_")
                .append(height)
                .append(".")
                .append(extension).toString();
    }

    @Override
    public Image changeImageResolution(MultipartFile originalImage, ResolutionType resolutionType) throws IOException {
        Image imageToChange = new Image(originalImage.getBytes());
        switch (resolutionType) {
            case LOW:
                imageToChange.resize(ImageSize.LOW_RESOLUTION_WIDTH, ImageSize.LOW_RESOLUTION_HEIGHT);
                break;
            case MEDIUM:
                imageToChange.resize(ImageSize.MEDIUM_RESOLUTION_WIDTH, ImageSize.MEDIUM_RESOLUTION_HEIGHT);
                break;
            case HIGH:
                imageToChange.resize(ImageSize.HIGH_RESOLUTION_WIDTH, ImageSize.HIGH_RESOLUTION_HEIGHT);
                break;
        }
        imageToChange.setOutputQuality(ImageSize.IMAGE_QUALITY);
        return imageToChange;
    }
}
