package lab2;

import lab1.ArrayList;
import lab1.List;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static lab2.MergeSort.mergeSort;
import static lab2.Utils.readSample;
import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {
    private final Comparator<Integer> cmp = Integer::compare;

    @Test
    void empty() {
        List<Integer> list = new ArrayList<>(new Integer[]{});
        mergeSort(list, cmp);
        List<Integer> expected = new ArrayList<>(new Integer[]{});
        assertEquals(expected, list);
    }

    @Test
    void singleItem() {
        List<Integer> list = new ArrayList<>(new Integer[]{1});
        mergeSort(list, cmp);
        List<Integer> expected = new ArrayList<>(new Integer[]{1});
        assertEquals(expected, list);
    }

    @Test
    void twoItems() {
        List<Integer> list = new ArrayList<>(new Integer[]{4, 3});
        mergeSort(list, cmp);
        List<Integer> expected = new ArrayList<>(new Integer[]{3, 4});
        assertEquals(expected, list);
    }

    @Test
    void sorted() {
        List<Integer> list = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        mergeSort(list, cmp);
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        assertEquals(expected, list);
    }

    @Test
    void sortedNegatively() {
        List<Integer> list = new ArrayList<>(new Integer[]{8, 7, 6, 5, 4, 3, 2, 1});
        mergeSort(list, cmp);
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        assertEquals(expected, list);
    }

    @Test
    void sort100() {
        List<Integer> source = new ArrayList<>(readSample("lab2/100.txt"));
        mergeSort(source, cmp);
        List<Integer> expected = new ArrayList<>(readSample("lab2/100_sorted.txt"));
        assertEquals(expected, source);
    }

    @Test
    void sort1000() {
        List<Integer> source = new ArrayList<>(readSample("lab2/1000.txt"));
        mergeSort(source, cmp);
        List<Integer> expected = new ArrayList<>(readSample("lab2/1000_sorted.txt"));
        assertEquals(expected, source);
    }
}
