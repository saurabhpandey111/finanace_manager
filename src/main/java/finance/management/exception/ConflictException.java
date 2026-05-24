package finance.management.exception;

/** Thrown when a resource already exists or a conflict is detected. */
public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
