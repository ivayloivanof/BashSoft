package bg.softuni.io.comands;

import bg.softuni.exceptions.InvalidInputException;
import bg.softuni.io.interfaces.DirectoryManager;
import bg.softuni.judge.interfaces.CompareContent;
import bg.softuni.network.interfaces.AsynchDownloader;
import bg.softuni.repository.interfaces.Database;

/**
 * Created by ivanof on 7/2/16.
 */
public class TraverseFoldersCommand extends Command {

    public TraverseFoldersCommand(
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
        if (this.getData().length != 1 && this.getData().length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        if (this.getData().length == 1) {
            this.getIoManager().traverseDirectory(0);
        }

        if (this.getData().length == 2) {
            this.getIoManager().traverseDirectory(Integer.valueOf(this.getData()[1]));
        }
    }
}
