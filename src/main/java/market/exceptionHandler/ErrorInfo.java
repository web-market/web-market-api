package market.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorInfo {

    private String object;

    private String field;

    private String errorMessage;

    private Object invalidValue;

}
