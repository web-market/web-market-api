package market.repository;

import market.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {

    Media getById(Long id);

    List<Media> getByMediaFolderId(Long id);

    void deleteById(Long id);

}
