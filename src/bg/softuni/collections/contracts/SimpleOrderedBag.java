package bg.softuni.collections.contracts;

import java.util.Collection;

/**
 * Created by ivanof on 7/27/16.
 */
public interface SimpleOrderedBag<T extends Comparable<T>> extends Iterable<T> {
    void add(T element);
    void addAll(Collection<T> elements);
    int size();
    String joinWith(String joiner);
}
