package market.service;

public interface FileService {

    void removeFilesFromFolder(Long mediaId);

    void deleteFilesByMedia(Long mediaId);

}
