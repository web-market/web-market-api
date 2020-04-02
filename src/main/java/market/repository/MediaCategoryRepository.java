package market.repository;

import market.entity.MediaCategory;
import market.projection.mediaCategory.MediaCategoryInlineView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaCategoryRepository extends JpaRepository<MediaCategory, Long> {

    List<MediaCategoryInlineView> findAllBy();

    MediaCategory getById(Long id);

    void deleteAllByIdIn(List<Long> ids);

}
