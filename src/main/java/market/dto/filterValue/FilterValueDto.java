package market.dto.filterValue;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class FilterValueDto {

    private Long id;

    @NotNull
    private String value;

    @NotNull
    private Long filterId;

    private List<Long> productIds;
}
