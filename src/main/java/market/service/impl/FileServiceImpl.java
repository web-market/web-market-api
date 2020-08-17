package market.service.impl;

import lombok.RequiredArgsConstructor;
import market.dto.file.image.ImageDto;
import market.entity.File;
import market.entity.Media;
import market.projection.file.ImageFileView;
import market.repository.FileRepository;
import market.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Override
    public List<ImageFileView> getByMediaId(Long mediaId) {
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

    @Override
    @Transactional
    public void deleteByMediaId(Long id) {
        this.fileRepository.deleteAllByMediaId(id);
    }
}
