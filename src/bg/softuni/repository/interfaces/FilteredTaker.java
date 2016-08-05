package bg.softuni.repository.interfaces;

/**
 * Created by ivanof on 7/14/16.
 */
public interface FilteredTaker {

    void filterAndTake(String courseName, String filter);

    void filterAndTake(String courseName, String filter, int studentsToTake);
}
