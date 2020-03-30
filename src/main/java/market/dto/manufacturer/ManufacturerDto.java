package market.dto.manufacturer;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ManufacturerDto {


    private Long id;

    @NotNull
    private String name;

    private String description;

}
