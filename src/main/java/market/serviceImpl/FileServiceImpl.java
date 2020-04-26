package market.serviceImpl;

import lombok.RequiredArgsConstructor;
import market.dto.file.image.ImageDto;
import market.entity.File;
import market.entity.Media;
import market.repository.FileRepository;
import market.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Override
    @Transactional
    public File save(ImageDto image, String pathToImage, Media media) {
        File imageToStore = new File();
        imageToStore.setName(image.getName());
        imageToStore.setFormat(image.getExtension());
        imageToStore.setPath(pathToImage);
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
