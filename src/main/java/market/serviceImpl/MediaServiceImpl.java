package market.serviceImpl;

import lombok.RequiredArgsConstructor;
import market.dto.file.image.ImageDto;
import market.dto.media.MediaImageStoreDto;
import market.dto.media.MediaImageUploadDto;
import market.entity.Media;
import market.entity.MediaCategory;
import market.repository.MediaRepository;
import market.service.FileService;
import market.service.ImageManagementService;
import market.service.MediaCategoryService;
import market.service.MediaService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    public static final String UPLOADING_DIR = "D:/uploads/";

    private final FileService fileService;
    private final ImageManagementService imageManagementService;
    private final MediaCategoryService mediaCategoryService;
    private final MediaRepository mediaRepository;

    @Override
    @Transactional
    public Media createMedia(MediaCategory folder) {
        Media media = new Media();
        media.setMediaCategory(folder);
        media.setCreationDate(LocalDateTime.now());
        return this.mediaRepository.save(media);
    }

    @Override
    @Transactional
    public List<MediaImageStoreDto> store(MediaImageUploadDto images) throws IOException {
        List<MediaImageStoreDto> imagesToStore = this.prepareAllImages(images);
        for (MediaImageStoreDto dto : imagesToStore) {
            Path path = Paths.get(UPLOADING_DIR + dto.getMediaId());
            if (Files.notExists(path)) {
                Files.createDirectory(path);
                for (ImageDto image : dto.getImages()) {
                    String pathToImage = path + File.separator + image.getName();
                    ImageIO.write(image.getImage().getBufferedImage(),
                            FilenameUtils.getExtension(image.getName()), new File(pathToImage));
                    this.fileService.save(image, pathToImage, this.mediaRepository.getById(dto.getMediaId()));
                }
            }
        }
        return imagesToStore;
    }

    @Override
    @Transactional
    public void removeImages(Long mediaId) throws IOException {
        FileUtils.deleteDirectory(new File(UPLOADING_DIR + mediaId));
        this.mediaRepository.deleteById(mediaId);
        this.fileService.deleteByMediaId(mediaId);
    }

    @Override
    public List<MediaImageStoreDto> prepareAllImages(MediaImageUploadDto images) throws IOException {
        MediaCategory mediaCategory = this.mediaCategoryService.getById(images.getMediaCategoryId());
        List<MediaImageStoreDto> imagesToStore = new ArrayList<>();
        for (MultipartFile image : images.getFiles()) {
            List<ImageDto> imagesPrepared = this.imageManagementService.getInMentionedResolutions(image,
                    images.getLowResolution(), images.getMediumResolution(), images.getHighResolution());

            imagesToStore.add(this.getPreparedImages(imagesPrepared, mediaCategory));
        }
        return imagesToStore;
    }

    @Override
    public MediaImageStoreDto getPreparedImages(List<ImageDto> images, MediaCategory folder) {
        MediaImageStoreDto mediaImageStoreDto = new MediaImageStoreDto();
        mediaImageStoreDto.setMediaId(this.createMedia(folder).getId());
        mediaImageStoreDto.setImages(images);
        return mediaImageStoreDto;
    }
}
