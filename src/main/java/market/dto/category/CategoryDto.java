package market.dto.category;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.dto.transfer.Create;
import market.dto.transfer.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CategoryDto {

    @NotNull(groups = {Update.class})
    private Long id;

    @NotBlank(message = "{category.empty.name}", groups = {Create.class, Update.class})
    private String name;

    @NotNull(message = "{category.empty.isActive}", groups = {Create.class, Update.class})
    private Boolean isActive;

    @NotNull(message = "{category.empty.sortOrder}", groups = {Create.class, Update.class})
    private Long sortOrder;

    private String color;

    private Long parentCategoryId;

}
