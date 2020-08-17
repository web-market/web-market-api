package market.service;

import market.dto.mediaFolder.MediaFolderDto;
import market.entity.MediaFolder;
import market.projection.mediaFolder.MediaFolderDropdownView;
import market.projection.mediaFolder.MediaFolderEditView;
import market.projection.mediaFolder.MediaFolderInlineView;

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
