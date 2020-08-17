package market.repository;

import market.entity.MediaFolder;
import market.projection.mediaFolder.MediaFolderDropdownView;
import market.projection.mediaFolder.MediaFolderEditView;
import market.projection.mediaFolder.MediaFolderInlineView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaFolderRepository extends JpaRepository<MediaFolder, Long> {

    //Projections
    List<MediaFolderInlineView> findAllBy();

    List<MediaFolderDropdownView> getAllBy();

    List<MediaFolderDropdownView> findAllByIdIsNotInAndIdIsNot(List<Long> ids, Long currentId);

    MediaFolderEditView getMediaFolderEditViewById(Long id);

    //DTOs
    List<MediaFolder> getAllByParentFolderId(Long id);

    MediaFolder getById(Long id);

    void deleteAllByIdIn(List<Long> ids);

}
