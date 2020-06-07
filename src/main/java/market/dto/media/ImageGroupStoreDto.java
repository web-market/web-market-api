package market.dto.media;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import market.dto.file.image.ImageDto;

import java.util.List;

@Builder
@Getter
public class ImageGroupStoreDto {

    private Long mediaId;

    private List<ImageDto> images;

}
