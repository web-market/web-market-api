package market.dto.file.image;

import javaxt.io.Image;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
public class ImageDto {

    private String name;

    private String extension;

    private String size;

    private Image image;

}
