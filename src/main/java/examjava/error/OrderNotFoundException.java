package examjava.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "order not found")
public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String message) {
        super(String.valueOf(message));
    }
}
