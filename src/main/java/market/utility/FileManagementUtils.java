package market.utility;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.FilenameUtils;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@UtilityClass
public class FileManagementUtils {

    @SneakyThrows(IOException.class)
    public Path createDirectoryIfNotExist(Path path) {
        return Files.notExists(path) ? Files.createDirectory(path) : path;
    }

    public String createImageName(String originalName, int width, int height) {
        return new StringBuilder()
                .append(FilenameUtils.getBaseName(originalName))
                .append(width)
                .append("_")
                .append(height)
                .append(".")
                .append(FilenameUtils.getExtension(originalName))
                .toString();
    }

    public Path generatePathToImage(Path path, String imageName) {
        return path.resolve(imageName);
    }
}
