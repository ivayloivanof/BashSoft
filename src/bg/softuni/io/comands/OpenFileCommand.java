package bg.softuni.io.comands;

import bg.softuni.exceptions.InvalidInputException;
import bg.softuni.io.interfaces.DirectoryManager;
import bg.softuni.judge.interfaces.CompareContent;
import bg.softuni.network.interfaces.AsynchDownloader;
import bg.softuni.repository.interfaces.Database;
import bg.softuni.staticData.SessionData;

import java.awt.*;
import java.io.File;

/**
 * Created by ivanof on 7/2/16.
 */
public class OpenFileCommand extends Command {

    public OpenFileCommand(
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
        if (this.getData().length != 2) {
            throw new InvalidInputException(this.getInput());
        }

        String fileName = this.getData()[1];
        String filePath = SessionData.currentPath + "\\" + fileName;
        File file = new File(filePath);
        Desktop.getDesktop().open(file);
    }
}
