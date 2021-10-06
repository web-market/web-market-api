package market.exceptionHandler;

import market.exceptions.NotFoundException;
import market.exceptions.ProcessCompletedSupplyException;
import market.exceptions.WrongProductSeparationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(EntityNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(WrongProductSeparationException.class)
    public ResponseEntity<ApiError> handleWrongProductSeparationException(WrongProductSeparationException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(ProcessCompletedSupplyException.class)
    public ResponseEntity<ApiError> handleProcessCompletedSupplyException(ProcessCompletedSupplyException ex) {
        ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, "Supply has been already processed and in COMPLETE status" + ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ApiError> handleIOException(IOException ex) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                "Picture can not be saved due to some error." + ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
                "Some arguments are missed or invalid.",
                this.buildErrorResponse(ex.getBindingResult()));
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    private List<ErrorInfo> buildErrorResponse(BindingResult errorResult) {
        return errorResult.getAllErrors()
                .stream()
                .map(error -> {
                    FieldError fieldError = ((FieldError) error);
                    return new ErrorInfo(fieldError.getObjectName(),
                            fieldError.getField(),
                            fieldError.getDefaultMessage(),
                            fieldError.getRejectedValue());
                })
                .collect(Collectors.toList());
    }
}
