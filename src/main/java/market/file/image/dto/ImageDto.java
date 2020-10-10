package market.file.image.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ImageDto {

    private final String name;

    private final String extension;

    private final Integer size;

    private final String path;

}