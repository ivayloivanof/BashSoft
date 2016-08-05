package bg.softuni.repository;

import bg.softuni.exceptions.InvalidFilterException;
import bg.softuni.io.OutputWriter;
import bg.softuni.repository.interfaces.DataFilter;

import java.util.HashMap;
import java.util.function.Predicate;

public class RepositoryFilter implements DataFilter {

    private Predicate<Double> createFilter(String filterType) {
        switch (filterType) {
            case "excellent":
                return mark -> mark >= 5.0;
            case "average":
                return mark -> 3.5 <= mark && mark < 5.0;
            case "poor":
                return mark -> mark < 3.5;
            default:
                return null;
        }
    }

    @Override
    public void printFilteredStudents(HashMap<String, Double> studentsWithMark,
                                      String filterType,
                                      Integer numberOfStudents) {

        Predicate<Double> filter = createFilter(filterType);
        if (filter == null) {
            throw new InvalidFilterException();
        }

        int studentsCount = 0;
        for (String student : studentsWithMark.keySet()) {
            if (studentsCount >= numberOfStudents) {
                break;
            }

            Double mark = studentsWithMark.get(student);

            if (filter.test(mark)) {
                OutputWriter.printStudent(student, mark);
                studentsCount++;
            }
        }


    }
}

