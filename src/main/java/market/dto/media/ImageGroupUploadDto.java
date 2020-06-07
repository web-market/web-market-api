package market.dto.media;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.dto.file.image.ResolutionDto;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class ImageGroupUploadDto {

    private Long mediaFolderId;

    private MultipartFile[] images;

}
