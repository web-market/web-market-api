package market.mediaFolder.service.impl;

import lombok.RequiredArgsConstructor;
import market.mediaFolder.dto.MediaFolderDto;
import market.entity.MediaFolder;
import market.mediaFolder.dto.MediaFolderDropdownView;
import market.mediaFolder.dto.MediaFolderEditView;
import market.mediaFolder.dto.MediaFolderInlineView;
import market.mediaFolder.MediaFolderRepository;
import market.mediaFolder.service.MediaFolderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaFolderServiceImpl implements MediaFolderService {

    private final MediaFolderRepository mediaFolderRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<MediaFolderInlineView> getFoldersInline() {
        return this.mediaFolderRepository.findAllBy();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MediaFolderDropdownView> getFolders() {
        return this.mediaFolderRepository.findAllMediaFolderDropdownViewBy();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MediaFolderDropdownView> getAllowedParents(Long id) {
        return this.mediaFolderRepository.findAllByIdIsNotInAndIdIsNot(this.getChildFoldersIds(id), id);
    }

    @Override
    @Transactional(readOnly = true)
    public MediaFolderEditView getFolderToEdit(Long id) {
        return this.mediaFolderRepository.findMediaFolderEditViewById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public MediaFolder getFolder(Long id) {
        return this.mediaFolderRepository.getById(id);
    }

    @Override
    @Transactional
    public MediaFolder create(MediaFolderDto mediaFolderDto) {
        MediaFolder newFolder = this.modelMapper.map(mediaFolderDto, MediaFolder.class);
        newFolder.setParentFolder(this.mediaFolderRepository.getById(mediaFolderDto.getParentFolderId()));
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


    //TODO: Add proper functionality to this method
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
