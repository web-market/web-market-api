package market.media;

import market.entity.Media;
import market.media.dto.MediaImageGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {

    //Projections
    List<MediaImageGroup> findAllByMediaFolderId(Long id);

    MediaImageGroup findMediaImageGroupById(Long id);

    //DTOs
    Media getById(Long id);

    void deleteById(Long id);

    void deleteAllByIdIn(List<Long> ids);

}
