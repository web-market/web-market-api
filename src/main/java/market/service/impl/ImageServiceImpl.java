package market.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import market.dto.file.image.ImageDto;
import market.entity.File;
import market.entity.ImageSize;
import market.entity.Media;
import market.service.FileService;
import market.service.ImageService;
import market.utility.FileManagementUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final FileService fileService;
    private final MultipartProperties fileUploadProperties;

    @SneakyThrows(IOException.class)
    public File saveResized(MultipartFile image, Media media, int width, int height) {
        String imageName = FileManagementUtils.createImageName(image.getOriginalFilename(), width, height);
        Path path = FileManagementUtils.generatePathToImage(Paths.get(fileUploadProperties.getLocation() + media.getId()), imageName);

        ImageDto imageToSave = ImageDto.builder()
                .name(imageName)
                .extension(FilenameUtils.getExtension(image.getOriginalFilename()))
                .path(path.toString())
                .size(image.getBytes().length)
                .build();

        Thumbnails.of(image.getInputStream())
                .forceSize(width, height)
                .outputQuality(ImageSize.IMAGE_QUALITY)
                .toFile(Path.of(imageToSave.getPath()).toFile());

        return this.fileService.saveImage(imageToSave, media);
    }

}
