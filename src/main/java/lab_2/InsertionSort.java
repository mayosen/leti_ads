package lab_2;

import lab_1.List;

import java.util.Comparator;

public class InsertionSort {
    public static <T> void insertionSort(List<T> list, Comparator<T> comparator) {
        T left;
        T right;
        for (int i = 1; i < list.size(); i++) {
            int j = i;
            while (j > 0 && comparator.compare(left = list.get(j - 1), right = list.get(j)) > 0) {
                list.set(j - 1, right);
                list.set(j, left);
                j--;
            }
        }
    }
}
