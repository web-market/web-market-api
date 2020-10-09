package market.file.image.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class ImagesUploadDto {

    private Long mediaFolderId;

    private MultipartFile[] images;

}
