package market.repository;

import market.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {

    Media getById(Long id);

    void deleteById(Long id);

}
