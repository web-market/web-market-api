package market.product.rawProduct.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.dto.transfer.Create;
import market.dto.transfer.Update;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class RawProductDto {

    @NotNull(groups = {Update.class})
    private Long id;

    @NotNull(groups =  {Create.class, Update.class})
    private String name;

    private String description;

    @NotNull(groups = {Create.class, Update.class})
    private Long manufacturerId;

}
