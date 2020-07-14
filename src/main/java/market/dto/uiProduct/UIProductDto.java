package market.dto.uiProduct;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UIProductDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description; // <- beware HTML

    private Long sortOrder;

    private boolean isActive;

}
