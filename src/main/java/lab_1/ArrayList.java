package lab_1;

import java.util.Arrays;

public class ArrayList<E extends Comparable<E>> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array = new Object[DEFAULT_CAPACITY];
    private int size = 0;

    public ArrayList() { }

    public ArrayList(E[] elements) {
        ensureCapacity(elements.length);

        for (E e : elements) {
            add(e);
        }
    }

    private void ensureCapacity(int requiredElementsCount) {
        if (array.length < requiredElementsCount) {
            int capacity = Math.max(DEFAULT_CAPACITY, requiredElementsCount + (requiredElementsCount >> 1));
            array = Arrays.copyOf(array, capacity);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(E e) {
        ensureCapacity(size + 1);
        array[size] = e;
        size++;
    }

    @Override
    public boolean remove(Object e) {
        int index = indexOf(e);

        if (index == -1) {
            return false;
        }

        removeInternally(index);
        return true;
    }

    @Override
    public boolean remove(int index) {
        checkIndex(index);
        removeInternally(index);
        return true;
    }

    private void removeInternally(int index) {
        System.arraycopy(array, index + 1, array, index, size - 1 - index);
        array[size - 1] = null;
        size--;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return arrayItem(index);
    }

    @SuppressWarnings("unchecked")
    private E arrayItem(int index) {
        return (E) array[index];
    }

    @Override
    public E set(int index, E e) {
        E previous = get(index);
        array[index] = e;
        return previous;
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(e)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public void clear() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof List<?> other)) {
            return false;
        }

        if (size != other.size()) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!array[i].equals(other.get(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Быстрая сортировка.
     */
    @Override
    public void sort() {
        quickSort(0, size - 1);
    }

    private void quickSort(int start, int end) {
        if (start < end) {
            int partition = partition(start, end);
            quickSort(start, partition - 1);
            quickSort(partition + 1, end);
        }
    }

    private int partition(int start, int end) {
        E pivot = arrayItem(end);
        int swapPosition = start;

        for (int i = start; i < end; i++) {
            if (arrayItem(i).compareTo(pivot) <= 0) {
                swapElements(swapPosition, i);
                swapPosition++;
            }
        }

        swapElements(swapPosition, end);

        return swapPosition;
    }

    private void swapElements(int a, int b) {
        Object temp = array[a];
        array[a] = b;
        array[b] = temp;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ArrayList{size=");
        builder.append(size);
        builder.append(", array=[");
        for (int i = 0; i < size; i++) {
            builder.append(array[i]);
            if (i != size - 1) {
                builder.append(", ");
            }
        }
        builder.append("]}");
        return builder.toString();
    }
}
