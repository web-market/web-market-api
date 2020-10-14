package market.supply.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class IdentificationNumberCheckDto {

    @NotNull
    private String identificationNumber;

}
