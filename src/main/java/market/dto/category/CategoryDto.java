package market.dto.category;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CategoryDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Boolean isActive;

    private Long sortOrder;

    private String color;

    private Long parentCategoryId;

}
