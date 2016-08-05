package bg.softuni.repository.interfaces;

import java.util.HashMap;

/**
 * Created by ivanof on 7/14/16.
 */
public interface DataSorter {

    void printSortedStudents(
            HashMap<String, Double> courseData,
            String comparisonType,
            int numberOfStudents);
}
