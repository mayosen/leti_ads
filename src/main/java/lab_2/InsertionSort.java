package lab_2;

import lab_1.List;

import java.util.Comparator;

public class InsertionSort {
    public static <T> void insertionSort(List<T> list, Comparator<T> cmp) {
        insertionSort(list, 0, list.size(), cmp);
    }

    /**
     * Сортировка вставками.
     *
     * @param list список
     * @param from индекс начала (включительно)
     * @param to индекс конца (не включительно)
     */
    static <T> void insertionSort(List<T> list, int from, int to, Comparator<T> cmp) {
        T left;
        T right;
        for (int i = from + 1; i < to; i++) {
            int j = i;
            while (j > from && cmp.compare(left = list.get(j - 1), right = list.get(j)) > 0) {
                list.set(j - 1, right);
                list.set(j, left);
                j--;
            }
        }
    }
}
