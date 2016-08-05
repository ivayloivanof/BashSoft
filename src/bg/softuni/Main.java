package bg.softuni;

import bg.softuni.io.CommandInterpreter;
import bg.softuni.io.IOManager;
import bg.softuni.io.InputReader;
import bg.softuni.io.interfaces.DirectoryManager;
import bg.softuni.io.interfaces.Interpreter;
import bg.softuni.io.interfaces.Reader;
import bg.softuni.judge.Tester;
import bg.softuni.judge.interfaces.CompareContent;
import bg.softuni.network.DownloadManager;
import bg.softuni.network.interfaces.AsynchDownloader;
import bg.softuni.repository.RepositoryFilter;
import bg.softuni.repository.RepositorySorter;
import bg.softuni.repository.StudentRepository;
import bg.softuni.repository.interfaces.DataFilter;
import bg.softuni.repository.interfaces.DataSorter;
import bg.softuni.repository.interfaces.Database;

public class Main {

    public static void main(String[] args) {
        try {
            CompareContent tester = new Tester();
            AsynchDownloader downloadManager = new DownloadManager();
            DirectoryManager ioManager = new IOManager();
            DataFilter repositoriesFilters = new RepositoryFilter();
            DataSorter repositorySorter = new RepositorySorter();
            Database studentRepository = new StudentRepository(repositoriesFilters, repositorySorter);
            Interpreter currentInterpreter = new CommandInterpreter(
                    downloadManager,
                    tester,
                    ioManager,
                    studentRepository
            );
            Reader inputReader = new InputReader(currentInterpreter);
            inputReader.readCommands();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
