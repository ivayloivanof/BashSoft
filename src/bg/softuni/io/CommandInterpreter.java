package bg.softuni.io;

import bg.softuni.exceptions.InvalidInputException;
import bg.softuni.io.comands.*;
import bg.softuni.io.interfaces.DirectoryManager;
import bg.softuni.io.interfaces.Executable;
import bg.softuni.io.interfaces.Interpreter;
import bg.softuni.judge.interfaces.CompareContent;
import bg.softuni.network.interfaces.AsynchDownloader;
import bg.softuni.repository.interfaces.Database;

import java.io.IOException;

public class CommandInterpreter implements Interpreter {

    private AsynchDownloader downloadManager;
    private CompareContent tester;
    private DirectoryManager ioManager;
    private Database studentRepository;

    public CommandInterpreter(
            AsynchDownloader downloadManager,
            CompareContent tester,
            DirectoryManager inputOutputManager,
            Database studentRepository) {
        this.setDownloadManager(downloadManager);
        this.setTester(tester);
        this.setIoManager(inputOutputManager);
        this.setStudentRepository(studentRepository);
    }

    private AsynchDownloader getDownloadManager() {
        return this.downloadManager;
    }

    private void setDownloadManager(AsynchDownloader downloadManager) {
        this.downloadManager = downloadManager;
    }

    private CompareContent getTester() {
        return this.tester;
    }

    private void setTester(CompareContent tester) {
        this.tester = tester;
    }

    protected DirectoryManager getIoManager() {
        return this.ioManager;
    }

    private void setIoManager(DirectoryManager ioManager) {
        this.ioManager = ioManager;
    }

    private Database getStudentRepository() {
        return this.studentRepository;
    }

    private void setStudentRepository(Database studentRepository) {
        this.studentRepository = studentRepository;
    }

    private Executable parseCommand(String input, String[] data, String command) {
        switch (command) {
            case "open":
                return new OpenFileCommand(
                        input,
                        data,
                        this.getTester(),
                        this.getStudentRepository(),
                        this.getDownloadManager(),
                        this.getIoManager()
                );
            case "mkdir":
                return new MakeDirectoryCommand(
                        input,
                        data,
                        this.getTester(),
                        this.getStudentRepository(),
                        this.getDownloadManager(),
                        this.getIoManager()
                );
            case "ls":
                return new TraverseFoldersCommand(
                        input,
                        data,
                        this.getTester(),
                        this.getStudentRepository(),
                        this.getDownloadManager(),
                        this.getIoManager()
                );
            case "cmp":
                return new CompareFilesCommand(
                        input,
                        data,
                        this.getTester(),
                        this.getStudentRepository(),
                        this.getDownloadManager(),
                        this.getIoManager()
                );
            case "cdrel":
                return new ChangeRelativePathCommand(
                        input,
                        data,
                        this.getTester(),
                        this.getStudentRepository(),
                        this.getDownloadManager(),
                        this.getIoManager()
                );
            case "cdabs":
                return new ChangeAbsolutePathCommand(
                        input,
                        data,
                        this.getTester(),
                        this.getStudentRepository(),
                        this.getDownloadManager(),
                        this.getIoManager()
                );
            case "readdb":
                return new ReadDatabaseFromFileCommand(
                        input,
                        data,
                        this.getTester(),
                        this.getStudentRepository(),
                        this.getDownloadManager(),
                        this.getIoManager()
                );
            case "help":
                return new GetHelpCommand(
                        input,
                        data,
                        this.getTester(),
                        this.getStudentRepository(),
                        this.getDownloadManager(),
                        this.getIoManager()
                );
            case "show":
                return new ShowWantedCourseCommand(
                        input,
                        data,
                        this.getTester(),
                        this.getStudentRepository(),
                        this.getDownloadManager(),
                        this.getIoManager()
                );
//            case "filter":
//                this.tryPrintFilteredStudents(input, data);
//                break;
            case "order":
                return new PrintOrderedStudentsCommand(
                        input,
                        data,
                        this.getTester(),
                        this.getStudentRepository(),
                        this.getDownloadManager(),
                        this.getIoManager()
                );
            case "download":
                return new DownloadFileCommand(
                        input,
                        data,
                        this.getTester(),
                        this.getStudentRepository(),
                        this.getDownloadManager(),
                        this.getIoManager()
                );
            case "downloadasynch":
                return new DownloadFileOnNewThreadCommand(
                        input,
                        data,
                        this.getTester(),
                        this.getStudentRepository(),
                        this.getDownloadManager(),
                        this.getIoManager()
                );
            case "dropdb":
                return new DropDatabaseCommand(
                        input,
                        data,
                        this.getTester(),
                        this.getStudentRepository(),
                        this.getDownloadManager(),
                        this.getIoManager()
                );
            case "display":
                return new DisplayCommand(
                        input,
                        data,
                        this.getTester(),
                        this.getStudentRepository(),
                        this.getDownloadManager(),
                        this.getIoManager()
                );
            default:
                throw new InvalidInputException(input);
        }
    }

    public void interpretCommand(String input) throws IOException {
        String[] data = input.split("\\s+");
        String commandName = data[0].toLowerCase();
        try {
            Executable command = parseCommand(input, data, commandName);
            command.execute();
        } catch (Throwable t) {
            OutputWriter.writeMessageOnNewLine(t.getMessage());
        }
    }

}
