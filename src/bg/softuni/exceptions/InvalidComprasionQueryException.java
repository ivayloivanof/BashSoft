package bg.softuni.exceptions;

/**
 * Created by ivanof on 6/30/16.
 */
public class InvalidComprasionQueryException extends RuntimeException {

    private static final String INVALID_COMPARISON_QUERY =
            "The comparison query you want, does not exist in the context of the current program!";

    public InvalidComprasionQueryException() {
        super(INVALID_COMPARISON_QUERY);
    }

    public InvalidComprasionQueryException(String message) {
        super(message);
    }
}
