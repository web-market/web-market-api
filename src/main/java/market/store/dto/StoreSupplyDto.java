package market.store.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.dto.transfer.Create;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class StoreSupplyDto {

    @NotNull(groups = {Create.class})
    private Long id;

}
