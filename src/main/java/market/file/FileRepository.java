package market.file;

import market.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {

    //DTOs
    void deleteAllByMediaId(Long id);

}
