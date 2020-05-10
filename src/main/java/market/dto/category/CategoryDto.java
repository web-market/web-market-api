package market.dto.category;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CategoryDto {

    private Long id;

    @NotNull(message = "{category.invalid.name}")
    @NotBlank(message = "{category.empty.name}")
    private String name;

//    @Value("${some.key:false}")
    private Boolean isActive;

    @NotNull
    private Long sortOrder;

    private String color;

    private Long parentCategoryId;

}
