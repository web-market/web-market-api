package market.dto.product;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description; // <- beware HTML

    @NotNull
    private Double price;

    private Long quantity;

    private Long minimumQuantity;

    private Long sortOrder;

    private boolean isSubtractProduct;

    private boolean isActive;

    private Long manufacturerId;

    private List<Long> categoryIds;

    private List<Long> filterValueIds;

    //+images
}
