package market.file.image.service;

import market.file.image.dto.ImagesUploadDto;

import java.io.IOException;

public interface ImageService {

    void storeImages(ImagesUploadDto uploads) throws IOException;

}
