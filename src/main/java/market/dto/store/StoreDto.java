package market.dto.store;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class StoreDto {

    @NotNull(groups = {Update.class})
    private Long id;

    @NotBlank(groups = {Create.class, Update.class})
    private String name;

}
