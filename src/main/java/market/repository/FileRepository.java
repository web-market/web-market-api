package market.repository;

import market.entity.File;
import market.projection.file.ImageFileView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {

    //Projections
    List<ImageFileView> findAllByMediaId(Long id);

    //DTOs
    void deleteAllByMediaId(Long id);

}
