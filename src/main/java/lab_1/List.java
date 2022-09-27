package lab_1;

import java.util.Comparator;

public interface List<E> {
    int size();

    boolean isEmpty();

    void add(E e);

    void add(int index, E e);

    boolean remove(Object e);

    boolean remove(int index);

    E get(int index);

    E set(int index, E e);

    boolean contains(Object e);

    int indexOf(Object e);

    void clear();

    void sort(Comparator<E> comparator);
}
