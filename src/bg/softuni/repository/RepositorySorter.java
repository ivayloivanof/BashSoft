package bg.softuni.repository;

import bg.softuni.exceptions.InvalidComprasionQueryException;
import bg.softuni.io.OutputWriter;
import bg.softuni.repository.interfaces.DataSorter;

import java.util.*;
import java.util.stream.Collectors;

public class RepositorySorter implements DataSorter {

    private void printStudents(HashMap<String, Double> courseData, List<String> sortedStudents) {
        for (String student : sortedStudents) {
            OutputWriter.printStudent(student, courseData.get(student));
        }
    }

    @Override
    public void printSortedStudents(
            HashMap<String, Double> courseData,
            String comparisonType,
            int numberOfStudents) {
        comparisonType = comparisonType.toLowerCase();

        if (!comparisonType.equals("ascending") && !comparisonType.equals("descending")) {
            throw new InvalidComprasionQueryException();
        }

        Comparator<Map.Entry<String, Double>> comparator = (x, y) -> {
            double value1 = x.getValue();
            double value2 = y.getValue();
            return Double.compare(value1, value2);
        };

        List<String> sortedStudents = courseData.entrySet()
                .stream()
                .sorted(comparator)
                .limit(numberOfStudents)
                .map(x -> x.getKey())
                .collect(Collectors.toList());

        if (comparisonType.equals("descending")) {
            Collections.reverse(sortedStudents);
        }

        printStudents(courseData, sortedStudents);
    }
}

