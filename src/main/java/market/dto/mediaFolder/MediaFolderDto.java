package market.dto.mediaFolder;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.dto.transfer.Create;
import market.dto.transfer.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class MediaFolderDto {

    @NotNull(groups = {Update.class})
    private Long id;

    @NotBlank(groups = {Create.class, Update.class})
    private String name;

    private Long parentFolderId;

}
