package market.repository;

import market.entity.File;
import market.projection.file.ImageFileView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {

    List<ImageFileView> findAllByMediaId(Long id);

    void deleteAllByMediaId(Long id);

}
