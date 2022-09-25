package lab_1;

public interface List<E extends Comparable<E>> {
    int size();

    boolean isEmpty();

    void add(E e);

    boolean remove(Object e);

    boolean remove(int index);

    E get(int index);

    E set(int index, E e);

    boolean contains(Object e);

    int indexOf(Object e);

    void clear();

    boolean equals(Object o);

    /**
     * Сортировка элементов в естественном порядке.
     */
    void sort();
}
