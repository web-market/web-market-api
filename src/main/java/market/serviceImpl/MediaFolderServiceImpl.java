package market.serviceImpl;

import lombok.RequiredArgsConstructor;
import market.dto.mediaFolder.MediaFolderDto;
import market.entity.MediaFolder;
import market.projection.mediaFolder.MediaFolderDropdownView;
import market.projection.mediaFolder.MediaFolderInlineView;
import market.repository.MediaFolderRepository;
import market.service.MediaFolderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaFolderServiceImpl implements MediaFolderService {

    private final ModelMapper modelMapper;
    private final MediaFolderRepository mediaFolderRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MediaFolderInlineView> getAllInline() {
        return this.mediaFolderRepository.findAllBy();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MediaFolderDropdownView> getAllForDropdown() {
        return this.mediaFolderRepository.getAllBy();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MediaFolderDropdownView> getAvailableFolders(Long id) {
        return this.mediaFolderRepository.findAllByIdIsNotInAndIdIsNot(this.getChildFoldersIds(id), id);
    }

    @Override
    @Transactional(readOnly = true)
    public MediaFolder getById(Long id) {
        return this.mediaFolderRepository.getById(id);
    }

    @Override
    @Transactional
    public MediaFolder create(MediaFolderDto mediaFolderDto) {
        MediaFolder newFolder = this.modelMapper.map(mediaFolderDto, MediaFolder.class);
        if (mediaFolderDto.getParentFolderId() != null) {
            MediaFolder parentFolder = this.mediaFolderRepository.getById(mediaFolderDto.getParentFolderId());
            newFolder.setParentFolder(parentFolder);
        }
        return this.mediaFolderRepository.save(newFolder);
    }

    @Override
    @Transactional
    public MediaFolder update(MediaFolderDto mediaFolderDto) {
        MediaFolder mediaFolder = modelMapper.map(mediaFolderDto, MediaFolder.class);
        mediaFolder.setParentFolder(this.mediaFolderRepository.getById(mediaFolderDto.getParentFolderId()));
        return this.mediaFolderRepository.save(mediaFolder);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.mediaFolderRepository.deleteById(id);
    }


    @Override
    public void delete(Long id, Boolean deleteContent) {

    }


    private List<Long> getChildFoldersIds(Long id) {
        List<Long> childIds = new ArrayList<>();
        this.mediaFolderRepository.getAllByParentFolderId(id).forEach(child -> {
            childIds.add(child.getId());
            childIds.addAll(getChildFoldersIds(child.getId()));
        });
        return childIds;
    }
}
