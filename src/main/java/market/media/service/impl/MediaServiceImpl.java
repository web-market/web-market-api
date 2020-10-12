package market.media.service.impl;

import lombok.RequiredArgsConstructor;
import market.entity.Media;
import market.media.MediaRepository;
import market.media.dto.MediaImageGroup;
import market.media.service.MediaService;
import market.mediaFolder.service.MediaFolderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaFolderService mediaFolderService;
    private final MediaRepository mediaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MediaImageGroup> getAllMediaInFolder(Long mediaFolderId) {
        return this.mediaRepository.findAllByMediaFolderId(mediaFolderId);
    }

    @Override
    @Transactional(readOnly = true)
    public MediaImageGroup getMedia(Long id) {
        return this.mediaRepository.findMediaImageGroupById(id);
    }

    @Override
    @Transactional
    public Media create(Long mediaFolderId) {
        Media media = new Media();
        media.setMediaFolder(this.mediaFolderService.getFolder(mediaFolderId));
        media.setCreationDate(LocalDateTime.now());
        return this.mediaRepository.save(media);
    }

    @Transactional
    public void delete(Long mediaId) {
        this.mediaRepository.deleteById(mediaId);
    }

    @Override
    @Transactional
    public void deleteMany(List<Long> ids) {
        this.mediaRepository.deleteAllByIdIn(ids);
    }

}
