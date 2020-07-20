package market.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import market.dto.file.image.ImageDto;
import market.dto.media.ImageGroupStoreDto;
import market.dto.media.ImageGroupUploadDto;
import market.entity.Media;
import market.projection.media.MediaView;
import market.repository.MediaRepository;
import market.service.FileService;
import market.service.ImageGroupManagementService;
import market.service.MediaFolderService;
import market.service.MediaService;
import market.utility.FileManagementUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    public static final String UPLOADING_DIR = "/uploads/";

    private final FileService fileService;
    private final ImageGroupManagementService imageGroupManagementService;
    private final MediaFolderService mediaFolderService;
    private final MediaRepository mediaRepository;

    @Transactional(readOnly = true)
    public List<MediaView> getByMediaFolderId(Long id) {
        return this.mediaRepository.findAllByMediaFolderId(id);
    }

    @Transactional(readOnly = true)
    public MediaView getById(Long id) {
        return this.mediaRepository.findMediaViewById(id);
    }

    @Override
    @Transactional
    public Media createMedia(Long mediaFolderId) {
        Media media = new Media();
        media.setMediaFolder(this.mediaFolderService.getById(mediaFolderId));
        media.setCreationDate(LocalDateTime.now());
        return this.mediaRepository.save(media);
    }

    @Override
    @Transactional
    public void store(ImageGroupUploadDto uploads) {
        this.prepareToStore(uploads).forEach(dto -> {
            Path path = FileManagementUtils.createDirectoryIfNotExist(Paths.get(UPLOADING_DIR + dto.getMediaId()));
            this.saveImageGroup(dto.getImages(), path, dto.getMediaId());
        });
    }

    @Override
    @Transactional
    public void removeImages(Long mediaId) throws IOException {
        FileUtils.deleteDirectory(new File(UPLOADING_DIR + mediaId));
        this.mediaRepository.deleteById(mediaId);
        this.fileService.deleteByMediaId(mediaId);
    }

    private List<ImageGroupStoreDto> prepareToStore(ImageGroupUploadDto uploads) {
        return Arrays.stream(uploads.getImages())
                .map(image -> ImageGroupStoreDto.builder()
                        .mediaId(this.createMedia(uploads.getMediaFolderId()).getId())
                        .images(this.imageGroupManagementService.getPreparedGroup(image))
                        .build())
                .collect(Collectors.toList());
    }

    @SneakyThrows(IOException.class)
    private void saveImageGroup(List<ImageDto> images, Path path, Long mediaId) {
        for (ImageDto image : images) {
            Path pathToImage = FileManagementUtils.generatePathToImage(path, image.getName());
            Files.copy(new ByteArrayInputStream(image.getImage().getByteArray()),
                    pathToImage, StandardCopyOption.REPLACE_EXISTING);
            this.fileService.saveImage(image, pathToImage.toString(), this.mediaRepository.getById(mediaId));
        }
    }
}
