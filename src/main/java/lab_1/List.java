package lab_1;

public interface List<E extends Comparable<E>> {
    int size();

    boolean isEmpty();

    void add(E e);

    void add(int index, E e);

    boolean remove(Object e);

    boolean remove(int index);

    E get(int index);

    E set(int index, E e);

    default boolean contains(Object e) {
        return indexOf(e) != -1;
    }

    int indexOf(Object e);

    void clear();

    /**
     * Сортировка элементов в естественном порядке.
     */
    void sort();
}
