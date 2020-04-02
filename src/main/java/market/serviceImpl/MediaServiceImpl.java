package market.serviceImpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import market.dto.media.MediaDto;
import market.dto.media.MediaUploadDto;
import market.entity.MediaCategory;
import market.repository.MediaCategoryRepository;
import market.repository.MediaRepository;
import market.service.MediaService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*TODO: separate cloudinary service maybe also discuss how it can influence media service
   also (dow we really need builder pattern for mediaDto) */

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;
    private final MediaCategoryRepository mediaCategoryRepository;
    private final Cloudinary cloudinary;

    @Override
    public List<MediaDto> uploadMedia(MediaUploadDto mediaUploadDto) throws IOException {
        return this.uploadFiles(mediaUploadDto);
    }

    private List<MediaDto> uploadFiles(MediaUploadDto mediaUploadDto) throws IOException {
        List<MediaDto> uploadedFiles = new ArrayList<>();
        MediaCategory mediaCategory = this.mediaCategoryRepository.getOne(mediaUploadDto.getId());
        for (MultipartFile file : mediaUploadDto.getFiles()) {
            Map uploadResult = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            uploadedFiles.add(this.createMediaDto(file.getName(), mediaCategory, uploadResult));
        }
        return uploadedFiles;
    }

    private MediaDto createMediaDto(String fileName, MediaCategory mediaCategory, Map objProps) {
        return new MediaDto.Builder(fileName)
                .setPublicId(objProps.get("public_id").toString())
                .setFormat(objProps.get("format").toString())
                .setMediaType(objProps.get("resource_type").toString())
                .setUrl(objProps.get("url").toString())
                .setSecureUrl(objProps.get("secure_url").toString())
//                .setCreationDate(LocalDateTime.parse(objProps.get("created_at").toString(),
//                        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS'Z'")))
//                .setMediaCategory(mediaCategory)
                .build();
    }

}
