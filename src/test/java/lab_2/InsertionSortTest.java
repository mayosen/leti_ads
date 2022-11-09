package lab_2;

import lab_1.ArrayList;
import lab_1.List;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static lab_2.InsertionSort.insertionSort;
import static lab_2.Utils.readSample;
import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest {
    private static final Comparator<Integer> cmp = Integer::compare;

    @Test
    void empty() {
        List<Integer> list = new ArrayList<>(new Integer[]{});
        insertionSort(list, cmp);
        List<Integer> expected = new ArrayList<>(new Integer[]{});
        assertEquals(expected, list);
    }

    @Test
    void singleItem() {
        List<Integer> list = new ArrayList<>(new Integer[]{1});
        insertionSort(list, cmp);
        List<Integer> expected = new ArrayList<>(new Integer[]{1});
        assertEquals(expected, list);
    }

    @Test
    void twoItems() {
        List<Integer> list = new ArrayList<>(new Integer[]{4, 3});
        insertionSort(list, cmp);
        List<Integer> expected = new ArrayList<>(new Integer[]{3, 4});
        assertEquals(expected, list);
    }

    @Test
    void sorted() {
        List<Integer> list = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        insertionSort(list, cmp);
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        assertEquals(expected, list);
    }

    @Test
    void sortedNegatively() {
        List<Integer> list = new ArrayList<>(new Integer[]{8, 7, 6, 5, 4, 3, 2, 1});
        insertionSort(list, cmp);
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        assertEquals(expected, list);
    }

    @Test
    void random() {
        List<Integer> list = new ArrayList<>(new Integer[]{6, 5, 3, 1, 8, 7, 2, 4});
        insertionSort(list, cmp);
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        assertEquals(expected, list);
    }

    @Test
    void intervalFromStart() {
        List<Integer> list = new ArrayList<>(new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1});
        insertionSort(list, 0, 4, cmp);
        List<Integer> expected = new ArrayList<>(new Integer[]{6, 7, 8, 9, 5, 4, 3, 2, 1});
        assertEquals(expected, list);
    }

    @Test
    void intervalToEnd() {
        List<Integer> list = new ArrayList<>(new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1});
        insertionSort(list, 6, 9, cmp);
        List<Integer> expected = new ArrayList<>(new Integer[]{9, 8, 7, 6, 5, 4, 1, 2, 3});
        assertEquals(expected, list);
    }

    @Test
    void intervalMid() {
        List<Integer> list = new ArrayList<>(new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1});
        insertionSort(list, 2, 6, cmp);
        List<Integer> expected = new ArrayList<>(new Integer[]{9, 8, 4, 5, 6, 7, 3, 2, 1});
        assertEquals(expected, list);
    }

    @Test
    void sort100() {
        List<Integer> source = new ArrayList<>(readSample("100.txt"));
        insertionSort(source, cmp);
        List<Integer> expected = new ArrayList<>(readSample("100_sorted.txt"));
        assertEquals(expected, source);
    }

    @Test
    void sort1000() {
        List<Integer> source = new ArrayList<>(readSample("1000.txt"));
        insertionSort(source, cmp);
        List<Integer> expected = new ArrayList<>(readSample("1000_sorted.txt"));
        assertEquals(expected, source);
    }
}
