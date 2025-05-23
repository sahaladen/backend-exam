package examjava.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "product not found")
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String  message) {
        super(String.valueOf(message));
    }
}
