package examjava.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "customer not found")
public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String message) {
        super(String.valueOf(message));
    }
}
