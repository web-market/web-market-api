package market.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProductSupplyDto {

    @NotNull
    private Long id;

}
