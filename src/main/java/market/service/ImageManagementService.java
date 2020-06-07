package market.service;

import market.dto.file.image.ImageDto;
import market.dto.file.image.ResolutionDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageManagementService {

    List<ImageDto> getInMentionedResolutions(MultipartFile image, ResolutionDto resolutions) throws IOException;

}
