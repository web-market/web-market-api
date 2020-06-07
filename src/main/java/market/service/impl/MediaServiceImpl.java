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
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
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
//    @SneakyThrows(IOException.class)
    @Transactional
    public void store(ImageGroupUploadDto uploads) throws IOException {
        for (ImageGroupStoreDto dto : this.prepareToStore(uploads)) {
            Path path = FileManagementUtils.createDirectoryIfNotExist(Paths.get(UPLOADING_DIR + dto.getMediaId()));
            this.saveImageGroup(dto.getImages(), path, dto.getMediaId());
        }
    }

    @Override
    @Transactional
    public void removeImages(Long mediaId) throws IOException {
        FileUtils.deleteDirectory(new File(UPLOADING_DIR + mediaId));
        this.mediaRepository.deleteById(mediaId);
        this.fileService.deleteByMediaId(mediaId);
    }

    //    @SneakyThrows(IOException.class)
    private List<ImageGroupStoreDto> prepareToStore(ImageGroupUploadDto uploads) throws IOException {
        List<ImageGroupStoreDto> list = new ArrayList<>();
        for (MultipartFile image : uploads.getImages()) {
            ImageGroupStoreDto build = ImageGroupStoreDto.builder()
                    .mediaId(this.createMedia(uploads.getMediaFolderId()).getId())
                    .images(this.imageGroupManagementService.getPreparedGroup(image))
                    .build();
            list.add(build);
        }
        return list;
    }

    @SneakyThrows(IOException.class)
    private void saveImageGroup(List<ImageDto> images, Path path, Long mediaId) {
        for (ImageDto image : images) {
            String pathToImage = FileManagementUtils.generatePathToImage(path, image.getName());
            ImageIO.write(image.getImage().getBufferedImage(), image.getExtension(), new File(pathToImage));
            this.fileService.saveImage(image, pathToImage, this.mediaRepository.getById(mediaId));
        }
    }
}
