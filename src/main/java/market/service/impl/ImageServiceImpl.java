package market.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import market.dto.file.image.ImageDto;
import market.entity.File;
import market.entity.ImageSize;
import market.entity.Media;
import market.projection.file.ImageFileView;
import market.repository.FileRepository;
import market.service.ImageService;
import market.utility.FileManagementUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final FileRepository fileRepository;
    private final MultipartProperties fileUploadProperties;

    @Override
    @Transactional(readOnly = true)
    public List<ImageFileView> getImagesByMedia(Long mediaId) {
        return this.fileRepository.findAllByMediaId(mediaId);
    }

    @Override
    @Transactional
    public File saveImage(ImageDto image, Media media) {
        File imageToStore = new File();
        imageToStore.setName(image.getName());
        imageToStore.setFormat(image.getExtension());
        imageToStore.setPath(image.getPath());
        imageToStore.setSize(image.getSize());
        imageToStore.setMedia(media);
        return this.fileRepository.save(imageToStore);
    }

    @SneakyThrows(IOException.class)
    @Override
    @Transactional
    public File storeResizedImage(MultipartFile image, Media media, int width, int height) {
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

        return this.saveImage(imageToSave, media);
    }

}
