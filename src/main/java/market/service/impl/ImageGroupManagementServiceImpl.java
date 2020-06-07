package market.service.impl;

import javaxt.io.Image;
import lombok.SneakyThrows;
import market.dto.file.image.ImageDto;
import market.entity.ImageSize;
import market.service.ImageGroupManagementService;
import market.utility.FileManagementUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageGroupManagementServiceImpl implements ImageGroupManagementService {

    public List<ImageDto> getPreparedGroup(MultipartFile originalImage) {
        final String originalName = originalImage.getOriginalFilename();
        return this.getResizedGroup(originalImage).stream()
                .map(image -> {
                    String newName = FileManagementUtils.createImageName(originalName, image.getWidth(), image.getHeight());
                    return ImageDto.builder()
                            .name(newName)
                            .extension(FilenameUtils.getExtension(originalName))
                            .size(String.valueOf(image.getExifTags().size()))
                            .image(image)
                            .build();
                }).collect(Collectors.toList());
    }

    @SneakyThrows(IOException.class)
    private List<Image> getResizedGroup(MultipartFile originalImage) {
        List<Image> resizedGroup = new ArrayList<>();
        resizedGroup.add(new Image(originalImage.getBytes()));
        resizedGroup.add(this.getResizedImage(originalImage, ImageSize.LOW_RESOLUTION_WIDTH, ImageSize.LOW_RESOLUTION_HEIGHT));
        resizedGroup.add(this.getResizedImage(originalImage, ImageSize.MEDIUM_RESOLUTION_WIDTH, ImageSize.MEDIUM_RESOLUTION_HEIGHT));
        resizedGroup.add(this.getResizedImage(originalImage, ImageSize.HIGH_RESOLUTION_WIDTH, ImageSize.HIGH_RESOLUTION_HEIGHT));
        return resizedGroup;
    }

    @SneakyThrows(IOException.class)
    private Image getResizedImage(MultipartFile originalImage, int width, int height) {
        Image image = new Image(originalImage.getBytes());
        image.resize(width, height);
        image.setOutputQuality(ImageSize.IMAGE_QUALITY);
        return image;
    }

}
