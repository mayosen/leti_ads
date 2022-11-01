package lab_2;

import lab_1.ArrayList;
import lab_1.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest {
    @Test
    void empty() {
        List<Integer> list = new ArrayList<>(new Integer[]{});
        InsertionSort.insertionSort(list, Integer::compare);
        List<Integer> expected = new ArrayList<>(new Integer[]{});
        assertEquals(expected, list);
    }

    @Test
    void singleItem() {
        List<Integer> list = new ArrayList<>(new Integer[]{1});
        InsertionSort.insertionSort(list, Integer::compare);
        List<Integer> expected = new ArrayList<>(new Integer[]{1});
        assertEquals(expected, list);
    }

    @Test
    void twoItems() {
        List<Integer> list = new ArrayList<>(new Integer[]{4, 3});
        InsertionSort.insertionSort(list, Integer::compare);
        List<Integer> expected = new ArrayList<>(new Integer[]{3, 4});
        assertEquals(expected, list);
    }

    @Test
    void sorted() {
        List<Integer> list = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        InsertionSort.insertionSort(list, Integer::compare);
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        assertEquals(expected, list);
    }

    @Test
    void sortedNegatively() {
        List<Integer> list = new ArrayList<>(new Integer[]{8, 7, 6, 5, 4, 3, 2, 1});
        InsertionSort.insertionSort(list, Integer::compare);
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        assertEquals(expected, list);
    }

    @Test
    void random() {
        List<Integer> list = new ArrayList<>(new Integer[]{6, 5, 3, 1, 8, 7, 2, 4});
        InsertionSort.insertionSort(list, Integer::compare);
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        assertEquals(expected, list);
    }
}
