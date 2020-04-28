package market.repository;

import market.entity.MediaFolder;
import market.projection.mediaFolder.MediaFolderDropdownView;
import market.projection.mediaFolder.MediaFolderInlineView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaFolderRepository extends JpaRepository<MediaFolder, Long> {

    List<MediaFolderInlineView> findAllBy();

    List<MediaFolder> getAllByParentFolderId(Long id);

    List<MediaFolderDropdownView> findAllByIdIsNotInAndIdIsNot(List<Long> ids, Long currentId);

    MediaFolder getById(Long id);

    void deleteAllByIdIn(List<Long> ids);

}
