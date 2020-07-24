package market.dto.rawProduct;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.dto.transfer.Create;
import market.dto.transfer.Update;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class RawProductDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description; // <- beware HTML

    @NotNull(groups = {Create.class, Update.class})
    private Long manufacturerId;


}
