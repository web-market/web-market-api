package market.dto.category;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CategoryDropDto {

    @NotNull
    private Long id;

    private Boolean deleteSubCategories;

}
