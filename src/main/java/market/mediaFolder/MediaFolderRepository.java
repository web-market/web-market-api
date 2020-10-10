package market.mediaFolder;

import market.entity.MediaFolder;
import market.mediaFolder.dto.MediaFolderDropdownView;
import market.mediaFolder.dto.MediaFolderEditView;
import market.mediaFolder.dto.MediaFolderInlineView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaFolderRepository extends JpaRepository<MediaFolder, Long> {

    //Projections
    List<MediaFolderInlineView> findAllBy();

    List<MediaFolderDropdownView> findAllMediaFolderDropdownViewBy();

    List<MediaFolderDropdownView> findAllByIdIsNotInAndIdIsNot(List<Long> ids, Long currentId);

    MediaFolderEditView findMediaFolderEditViewById(Long id);

    //DTOs
    List<MediaFolder> getAllByParentFolderId(Long id);

    MediaFolder getById(Long id);

    void deleteAllByIdIn(List<Long> ids);

}