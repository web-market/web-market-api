package market.dto.file.image;

import javaxt.io.Image;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ImageDto {

    private final String name;

    private final String extension;

    private final String size;

    private final Image image;

}
