package market.projection.media;

import market.projection.file.ImageFileView;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface MediaView {

    Long getId();

    @Value("#{target.creationDate.toString()}")
    String getCreatedAtDate();

    List<ImageFileView> getFiles();

}
