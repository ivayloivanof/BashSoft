package bg.softuni.exceptions;

/**
 * Created by ivanof on 6/30/16.
 */
public class InvalidInputException extends RuntimeException {

    private static final String THE_COMMAND_IS_INVALID =
            "The command '%s' is invalid";

    public InvalidInputException(String message) {
        super(String.format(THE_COMMAND_IS_INVALID, message));
    }
}
