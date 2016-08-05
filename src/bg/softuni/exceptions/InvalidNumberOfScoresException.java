package bg.softuni.exceptions;

/**
 * Created by ivanof on 6/30/16.
 */
public class InvalidNumberOfScoresException extends RuntimeException {

    private static final String INVALID_NUMBER_OF_SCORES = "Invalid number of scores";

    public InvalidNumberOfScoresException() {
        super(INVALID_NUMBER_OF_SCORES);
    }

    public InvalidNumberOfScoresException(String message) {
        super(message);
    }
}
