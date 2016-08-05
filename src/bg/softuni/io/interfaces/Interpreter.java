package bg.softuni.io.interfaces;

import java.io.IOException;

/**
 * Created by ivanof on 7/14/16.
 */
public interface Interpreter {

    void interpretCommand(String input) throws IOException;
}
