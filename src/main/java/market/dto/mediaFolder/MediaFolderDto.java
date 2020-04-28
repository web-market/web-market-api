package market.dto.mediaFolder;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class MediaFolderDto {

    private Long id;

    @NotNull
    private String name;

    private Long parentFolderId;

}
