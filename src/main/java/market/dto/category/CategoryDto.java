package market.dto.category;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CategoryDto {

    private Long id;

    @NotNull
    private String name;

    private Boolean isActive;

    private Long sortOrder;

    private String color;

    private Boolean hasSubCategories;

}
