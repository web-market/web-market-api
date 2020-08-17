package market.service.impl;

import lombok.RequiredArgsConstructor;
import market.dto.media.ImageGroupUploadDto;
import market.entity.ImageSize;
import market.entity.Media;
import market.projection.media.MediaView;
import market.repository.MediaRepository;
import market.service.FileService;
import market.service.ImageService;
import market.service.MediaFolderService;
import market.service.MediaService;
import market.utility.FileManagementUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final FileService fileService;
    private final MediaFolderService mediaFolderService;
    private final MediaRepository mediaRepository;
    private final ImageService imageService;
    private final MultipartProperties fileUploadProperties;

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
    public void removeImages(Long mediaId) throws IOException {
        FileUtils.deleteDirectory(new File(fileUploadProperties.getLocation() + mediaId));
        this.mediaRepository.deleteById(mediaId);
        this.fileService.deleteByMediaId(mediaId);
    }

    @Override
    @Transactional
    public void store(ImageGroupUploadDto uploads) throws IOException {
        for (MultipartFile image : uploads.getImages()) {
            BufferedImage originalImage = ImageIO.read(image.getInputStream());
            Media media = this.createMedia(uploads.getMediaFolderId());

            FileManagementUtils.createDirectoryIfNotExist(Paths.get(fileUploadProperties.getLocation() + media.getId()));

            this.imageService.saveResized(image, media, originalImage.getWidth(), originalImage.getHeight());

            this.imageService.saveResized(image, media, ImageSize.LOW_RESOLUTION_WIDTH, ImageSize.LOW_RESOLUTION_HEIGHT);
            this.imageService.saveResized(image, media, ImageSize.MEDIUM_RESOLUTION_WIDTH, ImageSize.MEDIUM_RESOLUTION_HEIGHT);
            this.imageService.saveResized(image, media, ImageSize.HIGH_RESOLUTION_WIDTH, ImageSize.HIGH_RESOLUTION_HEIGHT);
        }

    }

}
