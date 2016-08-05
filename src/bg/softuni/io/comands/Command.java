package bg.softuni.io.comands;

import bg.softuni.io.interfaces.DirectoryManager;
import bg.softuni.io.interfaces.Executable;
import bg.softuni.judge.interfaces.CompareContent;
import bg.softuni.network.interfaces.AsynchDownloader;
import bg.softuni.repository.interfaces.Database;

/**
 * Created by ivanof on 7/1/16.
 */
public abstract class Command implements Executable {

    private Database studentRepository;
    private CompareContent tester;
    private DirectoryManager ioManager;
    private AsynchDownloader downloadManager;
    private String input;
    private String[] data;

    protected Command(
            String input,
            String[] data,
            CompareContent tester,
            Database studentRepository,
            AsynchDownloader downloadManager,
            DirectoryManager ioManager) {
        this.setInput(input);
        this.setData(data);
        this.tester = tester;
        this.studentRepository = studentRepository;
        this.ioManager = ioManager;
        this.downloadManager = downloadManager;
    }

    protected String getInput() {
        return this.input;
    }

    protected void setInput(String input) {
        if (input == null || input.equals("")) {
            //invalid command exception
        }
        this.input = input;
    }

    protected String[] getData() {
        return this.data;
    }

    protected void setData(String[] data) {
        if (data == null || data.equals("")) {
            //invalid command exception
        }
        this.data = data;
    }

    protected AsynchDownloader getDownloadManager() {
        return this.downloadManager;
    }

    protected DirectoryManager getIoManager() {
        return this.ioManager;
    }

    protected CompareContent getTester() {
        return this.tester;
    }

    protected Database getStudentRepository() {
        return this.studentRepository;
    }

    public abstract void execute() throws Exception;
}
