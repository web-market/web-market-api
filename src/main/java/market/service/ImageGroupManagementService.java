package market.service;

import market.dto.file.image.ImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageGroupManagementService {

    List<ImageDto> getPreparedGroup(MultipartFile originalImage);

}
