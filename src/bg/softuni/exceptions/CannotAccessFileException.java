package bg.softuni.exceptions;

/**
 * Created by ivanof on 6/30/16.
 */
public class CannotAccessFileException extends RuntimeException {

    private static final String CANNOT_ACCESS_FILE = "Cannot access file.";

    public CannotAccessFileException() {
        super(CANNOT_ACCESS_FILE);
    }

    public CannotAccessFileException(String message) {
        super(message);
    }
}
