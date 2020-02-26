package market.dto.category;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDropDto {

    private Long id;
    private Boolean deleteSubCategories;

}
