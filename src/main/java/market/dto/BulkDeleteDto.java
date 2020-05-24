package market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class BulkDeleteDto {

    @NotNull
    private List<Long> ids;
}