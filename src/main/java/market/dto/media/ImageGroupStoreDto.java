package market.dto.media;

import lombok.Builder;
import lombok.Getter;
import market.dto.file.image.ImageDto;

import java.util.List;

@Builder
@Getter
public class ImageGroupStoreDto {

    private final Long mediaId;

    private final List<ImageDto> images;

}
