package market.filterValue.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import market.dto.transfer.Create;
import market.dto.transfer.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class FilterValueDto {

    @NotNull(groups = {Update.class})
    private Long id;

    @NotBlank(groups = {Create.class, Update.class})
    private String value;

    @NotNull(groups = {Create.class, Update.class})
    private Long sortOrder;

    @NotNull(groups = {Create.class, Update.class})
    private Long filterId;

    private List<Long> productVariantIds;
}
