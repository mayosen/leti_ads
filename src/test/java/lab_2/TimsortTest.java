package lab_2;

import lab_1.ArrayList;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static lab_2.Timsort.*;
import static org.junit.jupiter.api.Assertions.*;

class TimsortTest {
    private final Comparator<Integer> cmp = Integer::compare;

    private void printMinrun(int value) {
        System.out.format("minrun for %d = %d\n", value, getMinRun(value));
    }

    @Test
    void minrun() {
        printMinrun(1);
        printMinrun(2);
        printMinrun(3);
        printMinrun(4);
        printMinrun(32);
        printMinrun(45);
        printMinrun(64);
        printMinrun(100);
        printMinrun(269);
        printMinrun(356);
        printMinrun(1_000);
        printMinrun(1_000_000);
        printMinrun(10_000_000);
        printMinrun(100_000_000);
        printMinrun(1_000_000_000);
    }

    @Test
    void runs() {
        ArrayList<Integer> list = new ArrayList<>(new Integer[]{
                1, 2, 3, 25, 23, 35, 4, 5, 7, 9, 13, 6, 8, 7, 6, 5, 4, 3, 2, 1
        });
        timsort(list, cmp);
    }

    @Test
    void mergeEmpty() {
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        ArrayList<Integer> expected = new ArrayList<>();
        assertEquals(expected, merge(left, right, cmp));
    }

    @Test
    void mergeLeftEmptySingle() {
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>(new Integer[]{1});
        ArrayList<Integer> expected = new ArrayList<>(new Integer[]{1});
        assertEquals(expected, merge(left, right, cmp));
    }

    @Test
    void mergeLeftEmpty() {
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>(new Integer[]{1, 2, 3});
        ArrayList<Integer> expected = new ArrayList<>(new Integer[]{1, 2, 3});
        assertEquals(expected, merge(left, right, cmp));
    }

    @Test
    void mergeFull() {
        ArrayList<Integer> left = new ArrayList<>(new Integer[]{80, 91, 1000});
        ArrayList<Integer> right = new ArrayList<>(new Integer[]{1, 2, 3, 100, 999});
        ArrayList<Integer> expected = new ArrayList<>(new Integer[]{1, 2, 3, 80, 91, 100, 999, 1000});
        assertEquals(expected, merge(left, right, cmp));
    }

    @Test
    void mergeWithGallop() {
        ArrayList<Integer> left = new ArrayList<>(new Integer[]{
                10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 200
        });
        ArrayList<Integer> right = new ArrayList<>(new Integer[]{1, 2, 3, 4, 100});
        ArrayList<Integer> expected = new ArrayList<>(new Integer[]{
                1, 2, 3, 4, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 100, 200
        });
        assertEquals(expected, merge(left, right, cmp));
    }

    @Test
    void mergeWithGallopMirrored() {
        ArrayList<Integer> left = new ArrayList<>(new Integer[]{
                10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 200
        });
        ArrayList<Integer> right = new ArrayList<>(new Integer[]{1, 2, 3, 4, 100});
        ArrayList<Integer> expected = new ArrayList<>(new Integer[]{
                1, 2, 3, 4, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 100, 200
        });
        assertEquals(expected, merge(right, left, cmp));
    }

    @Test
    void mergeWithGallopWithExactStepSize() {
        ArrayList<Integer> left = new ArrayList<>(new Integer[]{
                10, 11, 12, 13, 14, 15, 16, 170
        });
        ArrayList<Integer> right = new ArrayList<>(new Integer[]{1, 2, 3, 4, 100});
        ArrayList<Integer> expected = new ArrayList<>(new Integer[]{
                1, 2, 3, 4, 10, 11, 12, 13, 14, 15, 16, 100, 170
        });
        assertEquals(expected, merge(left, right, cmp));
    }

    @Test
    void mergeWithGallopWithStepSizePlus1() {
        ArrayList<Integer> left = new ArrayList<>(new Integer[]{
                10, 11, 12, 13, 14, 15, 16, 17
        });
        ArrayList<Integer> right = new ArrayList<>(new Integer[]{1, 2, 3, 4, 100});
        ArrayList<Integer> expected = new ArrayList<>(new Integer[]{
                1, 2, 3, 4, 10, 11, 12, 13, 14, 15, 16, 17, 100
        });
        assertEquals(expected, merge(left, right, cmp));
    }

    @Test
    void findSeriesEndFirst() {
        ArrayList<Integer> list = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        assertEquals(0, findSeriesEnd(list, 0, Integer.valueOf(1), cmp));
    }

    @Test
    void findSeriesEndLast() {
        ArrayList<Integer> list = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        assertEquals(9, findSeriesEnd(list, 0, Integer.valueOf(10), cmp));
    }

    @Test
    void findSeriesEndMiddle() {
        ArrayList<Integer> list = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        assertEquals(4, findSeriesEnd(list, 0, Integer.valueOf(5), cmp));
    }

    @Test
    void findSeriesEndRandom() {
        ArrayList<Integer> list = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        assertEquals(6, findSeriesEnd(list, 4, Integer.valueOf(7), cmp));
    }
}