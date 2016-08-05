package bg.softuni.exceptions;

/**
 * Created by ivanof on 6/30/16.
 */
public class KeyNotFoundException extends RuntimeException {

    private static final String NOT_ENROLLED_IN_COURSE = "Not enrolled in course!";

    public KeyNotFoundException() {
        super(NOT_ENROLLED_IN_COURSE);
    }

    public KeyNotFoundException(String message) {
        super(message);
    }
}
