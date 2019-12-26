package market.dto.category;

import lombok.Data;
import java.util.List;

@Data
public class CategorySideMenuDto {

    private Long id;

    private String name;

    private Boolean isActive;

    private Long sortOrder;

    private CategoryDto parentCategory;

    private List<CategoryDto> childCategories;
}
