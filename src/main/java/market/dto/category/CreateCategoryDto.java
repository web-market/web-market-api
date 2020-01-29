package market.dto.category;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CreateCategoryDto {

    private Long id;

    private String name;

    private Boolean isActive;

    private Long sortOrder;

    private String color;

    private Long parentCategoryId;

}
