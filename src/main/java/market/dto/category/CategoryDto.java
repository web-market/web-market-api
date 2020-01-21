package market.dto.category;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import market.annotation.UniqueName;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CategoryDto {

    private Long id;

    @NotNull
    @UniqueName
    private String name;

    private Boolean isActive;

    private Long sortOrder;

    private String color;

    private Boolean hasSubCategories;

}
