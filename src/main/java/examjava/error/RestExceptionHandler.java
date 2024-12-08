package examjava.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = CustomerNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleCustomerNotFoundException() {
        ExceptionResponse response = ExceptionResponse.builder().status(HttpStatus.NOT_FOUND.value())
                .reason("customer not found")
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CustomerAddressNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleCustomerAddressNotFoundException() {
        ExceptionResponse response = ExceptionResponse.builder().status(HttpStatus.NOT_FOUND.value())
                .reason("customer address not found")
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleProductNotFoundException() {
        ExceptionResponse response = ExceptionResponse.builder().status(HttpStatus.NOT_FOUND.value())
                .reason("product not found")
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
