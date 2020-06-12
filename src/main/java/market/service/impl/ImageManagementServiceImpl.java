package market.service.impl;

import javaxt.io.Image;
import lombok.RequiredArgsConstructor;
import market.dto.file.image.ImageDto;
import market.dto.file.image.ResolutionDto;
import market.entity.ImageSize;
import market.entity.ResolutionType;
import market.service.ImageManagementService;
import market.utility.FileManagementUtils;
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
    public List<ImageDto> getInMentionedResolutions(MultipartFile originalImage, ResolutionDto resolutions) throws IOException {
        List<ImageDto> images = new ArrayList<>();
        String originalImageName = originalImage.getOriginalFilename();
        Image originalResolutionImage = new Image(originalImage.getBytes());
        images.add(this.prepareImageToStore(originalResolutionImage, originalImageName));
        if (resolutions.getLowResolution()) {
            Image lowResolutionImage = this.resizeImage(originalImage, ResolutionType.LOW);
            images.add(this.prepareImageToStore(lowResolutionImage, originalImageName));
        }
        if (resolutions.getMediumResolution()) {
            Image mediumResolutionImage = this.resizeImage(originalImage, ResolutionType.MEDIUM);
            images.add(this.prepareImageToStore(mediumResolutionImage, originalImageName));
        }
        if (resolutions.getHighResolution()) {
            Image highResolutionImage = this.resizeImage(originalImage, ResolutionType.HIGH);
            images.add(this.prepareImageToStore(highResolutionImage, originalImageName));
        }

        return images;
    }

    private Image resizeImage(MultipartFile originalImage, ResolutionType resolutionType) throws IOException {
        Image resizedImage = new Image(originalImage.getBytes());
        switch (resolutionType) {
            case LOW:
                resizedImage.resize(ImageSize.LOW_RESOLUTION_WIDTH, ImageSize.LOW_RESOLUTION_HEIGHT);
                break;
            case MEDIUM:
                resizedImage.resize(ImageSize.MEDIUM_RESOLUTION_WIDTH, ImageSize.MEDIUM_RESOLUTION_HEIGHT);
                break;
            case HIGH:
                resizedImage.resize(ImageSize.HIGH_RESOLUTION_WIDTH, ImageSize.HIGH_RESOLUTION_HEIGHT);
                break;
        }
        resizedImage.setOutputQuality(ImageSize.IMAGE_QUALITY);
        return resizedImage;
    }

    private ImageDto prepareImageToStore(Image resizedImage, String originalName) {
        return ImageDto.builder()
                .name(FileManagementUtils.createImageName(originalName, resizedImage.getWidth(), resizedImage.getHeight()))
                .extension(FilenameUtils.getExtension(originalName))
                .size(String.valueOf(resizedImage.getExifTags().size()))
                .image(resizedImage)
                .build();
    }
}
