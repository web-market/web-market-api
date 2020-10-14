package market.file.image.service.impl;

import lombok.RequiredArgsConstructor;
import market.entity.Media;
import market.file.image.dto.ImageDto;
import market.file.image.dto.ImagesUploadDto;
import market.file.image.service.ImageService;
import market.file.service.FileService;
import market.fileProcessing.imageResizing.ImageProcessingService;
import market.media.service.MediaService;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final FileService fileService;
    private final MediaService mediaService;
    private final ImageProcessingService imageProcessingService;
    private final MultipartProperties fileUploadProperties;


    @Override
    @Transactional
    public void storeImages(ImagesUploadDto uploads) throws IOException {
        for (var image : uploads.getImages()) {
            var media = this.mediaService.create(uploads.getMediaFolderId());
            Files.createDirectory(Paths.get(fileUploadProperties.getLocation() + media.getId()));

            var resizedImages = this.imageProcessingService.processImage(image, media);
            this.store(resizedImages, media);
        }
    }

    private void store(Map<ImageDto, BufferedImage> imageMap, Media media) throws IOException {
        for (var imageGroup : imageMap.entrySet()) {
            this.fileService.save(imageGroup.getKey(), media);
            ImageIO.write(imageGroup.getValue(), imageGroup.getKey().getExtension(), new File(imageGroup.getKey().getPath()));
        }
    }

}
