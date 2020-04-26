package market.dto.media;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.entity.MediaCategory;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MediaDto {

    private LocalDateTime creationDate;

    private MediaCategory mediaCategory;
}
