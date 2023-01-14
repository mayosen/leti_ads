package lab2;

import lab1.List;

import java.util.Comparator;

public class MergeSort<T> {
    private final List<T> list;
    private final Comparator<T> cmp;

    private MergeSort(List<T> list, Comparator<T> cmp) {
        this.list = list;
        this.cmp = cmp;
    }

    public static <T> void mergeSort(List<T> list, Comparator<T> cmp) {
        new MergeSort<>(list, cmp).mergeSort(0, list.size());
    }

    private void mergeSort(int from, int to) {
        if (to - from > 1) {
            int mid = (from + to) / 2;
            mergeSort(from, mid);
            mergeSort(mid, to);
            merge(from, mid, to);
        }
    }

    private void merge(int from, int mid, int to) {
        List<T> left = list.subList(from, mid);
        List<T> right = list.subList(mid, to);

        int l = 0;
        int r = 0;
        int current = from;

        while (l < left.size() && r < right.size()) {
            if (cmp.compare(left.get(l), right.get(r)) <= 0) {
                list.set(current, left.get(l));
                l++;
            } else {
                list.set(current, right.get(r));
                r++;
            }
            current++;
        }

        for (; l < left.size(); l++, current++) {
            list.set(current, left.get(l));
        }

        for (; r < right.size(); r++, current++) {
            list.set(current, right.get(r));
        }
    }
}
