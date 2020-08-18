package market.dto.uiProduct;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.dto.transfer.Create;
import market.dto.transfer.Update;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UIProductDto {

    @NotNull(groups = {Update.class})
    private Long id;

    @NotNull(groups =  {Create.class, Update.class})
    private String name;

    //TODO: beware HTML should be tracked because of XSS attacks
    @NotNull(groups =  {Create.class, Update.class})
    private String description;

    @NotNull(groups = {Create.class, Update.class})
    private boolean isActive;

    @NotNull(groups = {Create.class, Update.class})
    private Long sortOrder;

}
