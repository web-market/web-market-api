package market.product.productVariant.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.dto.transfer.Create;
import market.dto.transfer.Update;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductVariantDto {

    @NotNull(groups = {Update.class})
    private Long id;

    @NotNull(groups = {Create.class, Update.class})
    private String name;

    @NotNull(groups = {Create.class, Update.class})
    private String description;

    @NotNull(groups = {Create.class, Update.class})
    private Long rawProductId;

    private List<Long> filterValueIds;

}
