package bg.softuni.io;

import bg.softuni.io.interfaces.Interpreter;
import bg.softuni.io.interfaces.Reader;
import bg.softuni.staticData.SessionData;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InputReader implements Reader {

    private static final String END_COMMAND = "quit";
    private Interpreter interpreter;

    public InputReader(Interpreter interpreter) {
        this.setInterpreter(interpreter);
    }

    private Interpreter getInterpreter() {
        return this.interpreter;
    }

    private void setInterpreter(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    public void readCommands() throws Exception {
        OutputWriter.writeMessage(String.format("%s > ", SessionData.currentPath));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine().trim();

        while (!input.equals(END_COMMAND)) {
            this.interpreter.interpretCommand(input);
            OutputWriter.writeMessage(String.format("%s > ", SessionData.currentPath));

            input = reader.readLine().trim();
        }

        for (Thread thread : SessionData.threadPool) {
            thread.join();
        }
    }
}
