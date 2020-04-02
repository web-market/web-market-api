package market.dto.media;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class MediaUploadDto {

    private Long id;

    private MultipartFile[] files;

}
