package finance.management.exception;

/** Thrown when client input is invalid or malformed. */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}

