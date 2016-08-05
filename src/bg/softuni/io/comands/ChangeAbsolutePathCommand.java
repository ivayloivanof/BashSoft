package bg.softuni.io.comands;

import bg.softuni.exceptions.InvalidInputException;
import bg.softuni.io.interfaces.DirectoryManager;
import bg.softuni.judge.interfaces.CompareContent;
import bg.softuni.network.interfaces.AsynchDownloader;
import bg.softuni.repository.interfaces.Database;

/**
 * Created by ivanof on 7/2/16.
 */
public class ChangeAbsolutePathCommand extends Command {

    public ChangeAbsolutePathCommand(
            String input,
            String[] data,
            CompareContent tester,
            Database studentRepository,
            AsynchDownloader downloadManager,
            DirectoryManager IOManager) {
        super(input, data, tester, studentRepository, downloadManager, IOManager);
    }

    @Override
    public void execute() throws Exception {
        if (this.getData().length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        String absolutePath = this.getData()[1];
        this.getIoManager().changeCurrentDirAbsolutePath(absolutePath);
    }
}
