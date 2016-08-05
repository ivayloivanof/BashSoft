package bg.softuni.collections;

import bg.softuni.collections.contracts.SimpleOrderedBag;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by ivanof on 7/27/16.
 */
public class SimpleSortedList<E extends Comparable<E>> implements SimpleOrderedBag<E> {

    private static final int DEFAULT_SIZE = 16;
    private int capacity;
    private E[] innerCollection;
    private int size;
    private Comparator<E> comparator;

    public SimpleSortedList(Class<E> type, Comparator<E> comparator) {
        this(type, comparator, DEFAULT_SIZE);
    }

    public SimpleSortedList(Class<E> type, Comparator<E> comparator, int capacity) {
        this.setCapacity(capacity);
        this.setComparator(comparator);
        this.initializeInnerCollection(type, capacity);
    }

    private void setCapacity(int capacity) {
        if(capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative!");
        }

        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    private void setComparator(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @SuppressWarnings("unchecked")
    private void initializeInnerCollection(Class<E> type, int capacity) {
        if(this.getCapacity() < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative!");
        }

        this.innerCollection = (E[]) Array.newInstance(type, capacity);
//        this.innerCollection = (E[]) new Object[capacity];
    }

    private void resize() {
        E[] newCollection = Arrays.copyOf(this.innerCollection, this.innerCollection.length * 2);
        this.innerCollection = newCollection;
    }

    private void multiResize(Collection<E> elements) {
        int newSize = this.innerCollection.length * 2;
        while (this.size() + elements.size() >= newSize) {
            newSize *= 2;
        }

        E[] newCollection = Arrays.copyOf(this.innerCollection, newSize);
        this.innerCollection = newCollection;
    }

    @Override
    public void add(E element) {
        if (this.size() == this.innerCollection.length) {
            this.resize();
        }

        this.innerCollection[this.size()] = element;
        this.size++;
        Arrays.sort(this.innerCollection, 0, this.size(), this.comparator);
    }

    @Override
    public void addAll(Collection<E> elements) {
        if(this.size() + elements.size() >= this.innerCollection.length) {
            this.multiResize(elements);
        }

        for (E element : elements) {
            this.innerCollection[this.size()] = element;
            this.size++;
        }
        Arrays.sort(this.innerCollection, 0, this.size(), this.comparator);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String joinWith(String joiner) {
        StringBuilder stringBuilder = new StringBuilder();
        for (E e : this) {
            stringBuilder
                    .append(e)
                    .append(joiner);
        }

        stringBuilder.setLength(stringBuilder.length() - joiner.length());

        return stringBuilder.toString();
    }

    @Override
    public Iterator<E> iterator() {

        Iterator<E> iterator = new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < size();
            }

            @Override
            public E next() {
                return innerCollection[this.index++];
            }
        };

        return iterator;
    }
}
