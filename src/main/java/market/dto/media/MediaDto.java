package market.dto.media;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.entity.MediaFolder;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MediaDto {

    private LocalDateTime creationDate;

    private MediaFolder mediaFolder;
}
