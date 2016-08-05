package bg.softuni.exceptions;

/**
 * Created by ivanof on 6/30/16.
 */
public class InvalidPathException extends RuntimeException {

    private static final String INVALID_PATH = "The course does not exist.";

    public InvalidPathException() {
        super(INVALID_PATH);
    }

    public InvalidPathException(String message) {
        super(message);
    }
}
