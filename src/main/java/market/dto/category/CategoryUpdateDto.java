package market.dto.category;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryUpdateDto {

    private Long id;

    private String name;

    private CategoryDropdownDto parentCategory;

    private Long sortOrder;

    private String color;

    private Boolean isActive;

}
