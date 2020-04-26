package market.dto.media;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.dto.file.image.ImageDto;

import java.util.List;

@Data
@NoArgsConstructor
public class MediaImageStoreDto {

    private Long mediaId;

    private List<ImageDto> images;

}
