package market.repository;

import market.entity.Media;
import market.projection.media.MediaView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {

    //Projections
    List<MediaView> findAllByMediaFolderId(Long id);

    MediaView findMediaViewById(Long id);

    //DTOs
    Media getById(Long id);

    void deleteById(Long id);

}
