package market.service;

import javaxt.io.Image;
import market.dto.file.image.ImageDto;
import market.entity.ResolutionType;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface ImageManagementService {

    Image changeImageResolution(MultipartFile originalImage, ResolutionType resolutionType) throws IOException;

    List<ImageDto> getInMentionedResolutions(MultipartFile image, Boolean lowResolution,
                                             Boolean mediumResolution, Boolean highResolution) throws IOException;

    String createImageName(String originalName, int width, int height);

}
