package market.dto.filter;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class FilterDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String displayName;

    private Long sortOrder;
}