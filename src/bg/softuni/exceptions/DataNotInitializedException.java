package bg.softuni.exceptions;

/**
 * Created by ivanof on 6/30/16.
 */
public class DataNotInitializedException extends RuntimeException {

    private static final String DATA_NOT_INITIALIZED = "Data is not initialized.";

    public DataNotInitializedException() {
        super(DATA_NOT_INITIALIZED);
    }

    public DataNotInitializedException(String message) {
        super(message);
    }
}
