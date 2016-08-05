package bg.softuni.repository.interfaces;

/**
 * Created by ivanof on 7/14/16.
 */
public interface OrderedTaker {

    void orderAndTake(String courseName, String orderType);

    void orderAndTake(String courseName, String orderType, int studentsToTake);
}
