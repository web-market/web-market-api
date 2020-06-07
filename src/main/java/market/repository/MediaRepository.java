package market.repository;

import market.entity.Media;
import market.projection.media.MediaView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {

    Media getById(Long id);

    List<MediaView> findAllByMediaFolderId(Long id);

    MediaView findMediaViewById(Long id);

    void deleteById(Long id);

}
