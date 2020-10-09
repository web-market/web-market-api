package market.mediaFolder.service;

import market.mediaFolder.dto.MediaFolderDto;
import market.entity.MediaFolder;
import market.mediaFolder.dto.MediaFolderDropdownView;
import market.mediaFolder.dto.MediaFolderEditView;
import market.mediaFolder.dto.MediaFolderInlineView;

import java.util.List;

public interface MediaFolderService {

    //Projections
    List<MediaFolderInlineView> getFoldersInline();

    List<MediaFolderDropdownView> getFolders();

    List<MediaFolderDropdownView> getAllowedParents(Long id);

    MediaFolderEditView getFolderToEdit(Long id);

    //DTOs
    MediaFolder getFolder(Long id);

    MediaFolder create(MediaFolderDto mediaFolderDto);

    MediaFolder update(MediaFolderDto mediaFolderDto);

    void delete(Long id);

    void delete(Long id, Boolean deleteContent);

}
