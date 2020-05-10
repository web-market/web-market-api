package market.service;

import market.dto.mediaFolder.MediaFolderDto;
import market.entity.MediaFolder;
import market.projection.mediaFolder.MediaFolderDropdownView;
import market.projection.mediaFolder.MediaFolderInlineView;

import java.util.List;

public interface MediaFolderService {

    List<MediaFolderInlineView> getAllInline();

    List<MediaFolderDropdownView> getAllForDropdown();

    List<MediaFolderDropdownView> getAvailableFolders(Long id);

    MediaFolder getById(Long id);

    MediaFolder create(MediaFolderDto mediaFolderDto);

    MediaFolder update(MediaFolderDto mediaFolderDto);

    void delete(Long id);

    void delete(Long id, Boolean deleteContent);

}
