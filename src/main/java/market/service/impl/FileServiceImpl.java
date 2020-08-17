package market.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import market.repository.FileRepository;
import market.service.FileService;
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

    @SneakyThrows(IOException.class)
    @Override
    public void removeFilesFromFolder(Long mediaId) {
        FileUtils.deleteDirectory(new File(fileUploadProperties.getLocation() + mediaId));
    }

    @Override
    @Transactional
    public void deleteFilesByMedia(Long mediaId) {
        this.fileRepository.deleteAllByMediaId(mediaId);
    }

}
