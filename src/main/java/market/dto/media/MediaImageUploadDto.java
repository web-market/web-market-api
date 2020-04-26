package market.dto.media;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class MediaImageUploadDto {

    private Long mediaCategoryId;

    private MultipartFile[] files;

    private Boolean lowResolution;

    private Boolean mediumResolution;

    private Boolean highResolution;
}
