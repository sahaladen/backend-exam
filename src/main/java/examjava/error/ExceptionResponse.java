package examjava.error;

import lombok.Builder;

@Builder
public record ExceptionResponse(
        int status,
        String reason
) {
}
