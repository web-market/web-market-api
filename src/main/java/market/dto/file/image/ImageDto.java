package market.dto.file.image;

import javaxt.io.Image;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ImageDto {

    private String name;

    private String extension;

    private String size;

    private Image image;

}
