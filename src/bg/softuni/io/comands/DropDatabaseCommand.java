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
public class DropDatabaseCommand extends Command {

    public DropDatabaseCommand(
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
        this.getStudentRepository().unloadData();
        OutputWriter.writeMessageOnNewLine("Database dropped!");
    }
}
