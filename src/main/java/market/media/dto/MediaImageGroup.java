package market.media.dto;

import market.file.image.dto.ImageFileView;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface MediaImageGroup {

    Long getId();

    @Value("#{target.creationDate.toString()}")
    String getCreatedAtDate();

    List<ImageFileView> getFiles();

}
