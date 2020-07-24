package market.dto.productVariant;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.dto.transfer.Create;
import market.dto.transfer.Update;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductVariantDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull(groups = {Create.class, Update.class})
    private Long rawProductId;

    private List<Long> filterValueIds;

}
