package market.fileProcessing.imageResizing.imageStaticFactory;

import market.file.image.dto.ImageDto;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class ImageStaticFactory {

    private static final double IMAGE_QUALITY = 1.0;

    public static BufferedImage createResizedImage(MultipartFile image, int width, int height) throws IOException {
        return Thumbnails.of(image.getInputStream())
                .forceSize(width, height)
                .outputQuality(IMAGE_QUALITY)
                .asBufferedImage();
    }

    public static ImageDto createImageAsFile(MultipartFile image, String location, Long mediaId, int width, int height) throws IOException {
        var imageName = createImageName(image.getOriginalFilename(), width, height);
        var extension = FilenameUtils.getExtension(image.getOriginalFilename());
        var path = createPathToImage(location, mediaId, imageName).toString();
        var size = image.getBytes().length;

        return ImageDto.builder()
                .name(imageName)
                .extension(extension)
                .path(path)
                .size(size)
                .build();
    }

    private static Path createPathToImage(String location, Long mediaId, String imageName) {
        return Paths.get(location + mediaId).resolve(imageName);
    }

    private static String createImageName(String originalName, int width, int height) {
        return FilenameUtils.getBaseName(originalName) +
                "_w" +
                width +
                "_h" +
                height +
                "." +
                FilenameUtils.getExtension(originalName);
    }
}
