package bg.softuni.io.comands;

import bg.softuni.collections.SimpleSortedList;
import bg.softuni.exceptions.InvalidInputException;
import bg.softuni.io.OutputWriter;
import bg.softuni.io.interfaces.DirectoryManager;
import bg.softuni.judge.interfaces.CompareContent;
import bg.softuni.models.SoftUniCourse.interfaces.Course;
import bg.softuni.models.SoftUniStudent.interfaces.Student;
import bg.softuni.network.interfaces.AsynchDownloader;
import bg.softuni.repository.interfaces.Database;

import java.util.Comparator;

/**
 * Created by ivanof on 7/2/16.
 */
public class DisplayCommand extends Command {

    public DisplayCommand(
            String input,
            String[] data,
            CompareContent tester,
            Database studentRepository,
            AsynchDownloader downloadManager,
            DirectoryManager ioManager) {
        super(input, data, tester, studentRepository, downloadManager, ioManager);
    }

    private Comparator<Course> createCourseComparator(String sortType) {
        switch (sortType){
            case "ascending":
                return (o1, o2) -> o1.compareTo(o2);

            case "descending":
                return (o1, o2) -> o2.compareTo(o1);

            default:
                throw new InvalidInputException(this.getInput());
        }
    }


    private Comparator<Student> createStudentComparator(String sortType) {
        switch (sortType){
            case "ascending":
                return Comparable::compareTo;

            case "descending":
                return (o1, o2) -> o2.compareTo(o1);

            default:
                throw new InvalidInputException(this.getInput());
        }
    }

    @Override
    public void execute() throws Exception {
        if (this.getData().length != 3) {
            throw new InvalidInputException(this.getInput());
        }

        String entityToDisplay = this.getData()[1];
        String sortType = this.getData()[2];

        if (entityToDisplay.equalsIgnoreCase("students")) {
            Comparator<Student> studentComparator = this.createStudentComparator(sortType);
            SimpleSortedList<Student> list = this.getStudentRepository().getAllStudentsSorted(studentComparator);
            OutputWriter.writeMessageOnNewLine(list.joinWith(System.lineSeparator()));
        } else if (entityToDisplay.equalsIgnoreCase("course")) {
            Comparator<Course> courseComparator = this.createCourseComparator(sortType);
            SimpleSortedList<Course> list = this.getStudentRepository().getAllCoursesSorted(courseComparator);
            OutputWriter.writeMessageOnNewLine(list.joinWith(System.lineSeparator()));
        }  else {
            throw new InvalidInputException(this.getInput());
        }
    }


}
