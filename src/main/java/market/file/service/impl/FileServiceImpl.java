package market.file.service.impl;

import lombok.RequiredArgsConstructor;
import market.entity.Media;
import market.file.FileRepository;
import market.file.image.dto.ImageDto;
import market.file.service.FileService;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final MultipartProperties fileUploadProperties;

    @Override
    @Transactional
    public market.entity.File save(ImageDto image, Media media) {
        market.entity.File imageToStore = new market.entity.File();
        imageToStore.setName(image.getName());
        imageToStore.setFormat(image.getExtension());
        imageToStore.setPath(image.getPath());
        imageToStore.setSize(image.getSize());
        imageToStore.setMedia(media);
        return this.fileRepository.save(imageToStore);
    }

    @Override
    public void removeFilesFromFolder(Long mediaId) throws IOException {
        FileUtils.deleteDirectory(new File(fileUploadProperties.getLocation() + mediaId));
    }

    @Override
    @Transactional
    public void deleteFilesByMedia(Long mediaId) {
        this.fileRepository.deleteAllByMediaId(mediaId);
    }

}
