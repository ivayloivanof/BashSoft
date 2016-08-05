package bg.softuni.exceptions;

/**
 * Created by ivanof on 6/30/16.
 */
public class InvalidStringException extends RuntimeException {

    private static final String NULL_OR_EMPTY_VALUE = "Canot be null or empty value.";

    public InvalidStringException() {
        super(NULL_OR_EMPTY_VALUE);
    }

    public InvalidStringException(String message) {
        super(message);
    }
}
