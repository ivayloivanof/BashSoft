package bg.softuni.exceptions;

/**
 * Created by ivanof on 6/30/16.
 */
public class DataAlreadyInitializedException extends RuntimeException {

    private static final String DATA_ALREADY_INITIALIZED = "Data is already initialized.";

    public DataAlreadyInitializedException() {
        super(DATA_ALREADY_INITIALIZED);
    }

    public DataAlreadyInitializedException(String message) {
        super(message);
    }
}
