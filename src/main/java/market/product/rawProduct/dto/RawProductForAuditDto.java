package market.product.rawProduct.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class RawProductForAuditDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

}
