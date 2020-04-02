package market.dto.mediaCategory;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class MediaCategoryDto {

    private Long id;

    @NotNull
    private String name;

    private Long parentCategoryId;

}
