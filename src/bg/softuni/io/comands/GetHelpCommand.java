package bg.softuni.io.comands;

import bg.softuni.exceptions.InvalidInputException;
import bg.softuni.io.OutputWriter;
import bg.softuni.io.interfaces.DirectoryManager;
import bg.softuni.judge.interfaces.CompareContent;
import bg.softuni.network.interfaces.AsynchDownloader;
import bg.softuni.repository.interfaces.Database;

/**
 * Created by ivanof on 7/2/16.
 */
public class GetHelpCommand extends Command {

    public GetHelpCommand(
            String input,
            String[] data,
            CompareContent tester,
            Database studentRepository,
            AsynchDownloader downloadManager,
            DirectoryManager ioManager) {
        super(input, data, tester, studentRepository, downloadManager, ioManager);
    }

    @Override
    public void execute() throws Exception {
        if (this.getData().length != 1) {
            throw new InvalidInputException(this.getInput());
        }

        this.displayHelp();
    }

    private void displayHelp() {
        StringBuilder helpBuilder = new StringBuilder();
        helpBuilder.append("make directory - mkdir nameOfFolder")
                .append(System.lineSeparator());
        helpBuilder.append("traverse directory - ls depth")
                .append(System.lineSeparator());
        helpBuilder.append("comparing files - cmp absolutePath1 absolutePath2")
                .append(System.lineSeparator());
        helpBuilder.append("change directory - cdRel relativePath or \"..\" for level up")
                .append(System.lineSeparator());
        helpBuilder.append("change directory - cdAbs absolutePath")
                .append(System.lineSeparator());
        helpBuilder.append("read students data base - readDb fileName")
                .append(System.lineSeparator());
        helpBuilder.append("filter students - filter {courseName} excellent/average/poor take 2/5/all")
                .append(System.lineSeparator());
        helpBuilder.append("order students - order {courseName} ascending/descending take 20/10/all")
                .append(System.lineSeparator());
        helpBuilder.append("download file - download URL (saved in current directory)")
                .append(System.lineSeparator());
        helpBuilder.append("download file on new thread - downloadAsynch URL (saved in the current directory)")
                .append(System.lineSeparator());
        helpBuilder.append("display data entities - display students/courses ascending /descending")
                .append(System.lineSeparator());
        helpBuilder.append("get help – help")
                .append(System.lineSeparator());
        OutputWriter.writeMessage(helpBuilder.toString());
        OutputWriter.writeEmptyLine();
    }
}
