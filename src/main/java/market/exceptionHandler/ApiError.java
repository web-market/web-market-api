package market.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private HttpStatus status;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ErrorInfo> errors;

    public ApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
