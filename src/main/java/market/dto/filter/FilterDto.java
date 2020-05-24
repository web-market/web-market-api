package market.dto.filter;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.dto.transfer.Create;
import market.dto.transfer.Update;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class FilterDto {

    @NotNull(groups = {Update.class})
    private Long id;

    @NotNull(groups = {Create.class, Update.class})
    private String name;

    @NotNull(groups = {Create.class, Update.class})
    private String displayName;

    @NotNull(groups = {Create.class, Update.class})
    private Long sortOrder;
}