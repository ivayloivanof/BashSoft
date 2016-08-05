package bg.softuni.repository.interfaces;

/**
 * Created by ivanof on 7/14/16.
 */
public interface Database extends Requester, FilteredTaker, OrderedTaker {

    void loadData(String fileName);

    void unloadData();
}
