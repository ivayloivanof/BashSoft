package bg.softuni.repository.interfaces;

import java.util.HashMap;

/**
 * Created by ivanof on 7/14/16.
 */
public interface DataFilter {

    void printFilteredStudents(HashMap<String, Double> studentsWithMark,
                               String filterType,
                               Integer numberOfStudents);
}
