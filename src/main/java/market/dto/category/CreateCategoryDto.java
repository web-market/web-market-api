package market.dto.category;

import lombok.Data;

@Data
public class CreateCategoryDto {

    private Long id;

    private String name;

    private Boolean isActive;

    private Long sortOrder;

    private String color;

    private Long parentCategoryId;

}
