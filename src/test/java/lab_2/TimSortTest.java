package lab_2;

import lab_1.ArrayList;
import lab_1.List;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;

import static lab_2.TimSort.*;
import static org.junit.jupiter.api.Assertions.*;

class TimSortTest {
    private final Comparator<Integer> cmp = Integer::compare;

    private void assertSorted(List<Integer> sorted) {
        Integer last = sorted.get(0);

        for (int i = 1; i < sorted.size(); i++) {
            Integer current = sorted.get(i);
            if (current >= last) {
                last = current;
            } else {
                System.out.println(elementsString(sorted));
                throw new AssertionError(String.format("element[%d] %d > element[%d] %d", i - 1, last, i, current));
            }
        }
    }

    private void printMinRun(int value) {
        System.out.format("minRun for %d = %d\n", value, getMinRun(value));
    }

    @Test
    void minRun() {
        printMinRun(1);
        printMinRun(2);
        printMinRun(3);
        printMinRun(4);
        printMinRun(32);
        printMinRun(45);
        printMinRun(64);
        printMinRun(100);
        printMinRun(269);
        printMinRun(356);
        printMinRun(1_000);
        printMinRun(1_000_000);
        printMinRun(10_000_000);
        printMinRun(100_000_000);
        printMinRun(1_000_000_000);
    }

    @Test
    void mergeEmpty() {
        List<Integer> list = new ArrayList<>();
        merge(list, new SubList(0, 0), new SubList(0, 0), cmp);
        List<Integer> expected = new ArrayList<>();
        assertEquals(expected, list);
    }

    @Test
    void mergeTwoOrdered() {
        List<Integer> list = new ArrayList<>(new Integer[]{1, 2});
        merge(list, new SubList(0, 1), new SubList(1, 2), cmp);
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 2});
        assertEquals(expected, list);
    }

    @Test
    void mergeTwoUnordered() {
        List<Integer> list = new ArrayList<>(new Integer[]{2, 1});
        merge(list, new SubList(0, 1), new SubList(1, 2), cmp);
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 2});
        assertEquals(expected, list);
    }


    @Test
    void mergeComplex() {
        List<Integer> list = new ArrayList<>(new Integer[]{100, 1, 2, 3, 4, 5, 20, -10, -5, 4, 21, 300, -100});
        merge(list, new SubList(1, 7), new SubList(7, 12), cmp);
        List<Integer> expected = new ArrayList<>(new Integer[]{100, -10, -5, 1, 2, 3, 4, 4, 5, 20, 21, 300, -100});
        assertEquals(expected, list);
    }


    @Test
    void findSeriesEndFirst() {
        List<Integer> list = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        assertEquals(0, findSeriesEnd(list, 0, 10, 1, cmp));
    }

    @Test
    void findSeriesEndNotFound() {
        List<Integer> list = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        assertEquals(-1, findSeriesEnd(list, 1, 10, 1, cmp));
    }


    @Test
    void findSeriesEndLast() {
        List<Integer> list = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        assertEquals(9, findSeriesEnd(list, 5, 10, 10, cmp));
    }

    @Test
    void findSeriesEndMiddle() {
        List<Integer> list = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        assertEquals(4, findSeriesEnd(list, 2, 8, 5, cmp));
    }

    @Test
    void mergeWithGallop() {
        List<Integer> source = new ArrayList<>();
        source.add(1000);
        IntStream.iterate(100, x -> x + 1).limit(50).forEach(source::add);
        IntStream.iterate(1, x -> x + 1).limit(50).forEach(source::add);
        source.add(1000);

        merge(source, new SubList(1, 51), new SubList(51, 102), cmp);

        List<Integer> expected = new ArrayList<>();
        expected.add(1000);
        IntStream.iterate(1, x -> x + 1).limit(50).forEach(expected::add);
        IntStream.iterate(100, x -> x + 1).limit(50).forEach(expected::add);
        expected.add(1000);

        assertEquals(expected, source);
    }


    @Test
    void mergeWithGallopMirrored() {
        List<Integer> source = new ArrayList<>();
        source.add(1000);
        IntStream.iterate(1, x -> x + 1).limit(50).forEach(source::add);
        IntStream.iterate(100, x -> x + 1).limit(50).forEach(source::add);
        source.add(1000);

        merge(source, new SubList(1, 51), new SubList(51, 102), cmp);

        List<Integer> expected = new ArrayList<>();
        expected.add(1000);
        IntStream.iterate(1, x -> x + 1).limit(50).forEach(expected::add);
        IntStream.iterate(100, x -> x + 1).limit(50).forEach(expected::add);
        expected.add(1000);

        assertEquals(expected, source);
    }

    @Test
    void mergeWithGallopWithExactStepSize() {
        List<Integer> source = new ArrayList<>(new Integer[]{
                1000, 1, 2, 3, 4, 5, 6, 7, 11, 12, 13, 14, 15, 1000
        });
        merge(source, new SubList(1, 8), new SubList(8, 13), cmp);
        List<Integer> expected = new ArrayList<>(new Integer[]{
                1000, 1, 2, 3, 4, 5, 6, 7, 11, 12, 13, 14, 15, 1000
        });
        assertEquals(expected, source);
    }

    @Test
    void mergeWithGallopWithStepSizePlus1() {
        List<Integer> source = new ArrayList<>(new Integer[]{
                1000, 1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 13, 14, 15, 1000
        });
        merge(source, new SubList(1, 9), new SubList(9, 14), cmp);
        List<Integer> expected = new ArrayList<>(new Integer[]{
                1000, 1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 13, 14, 15, 1000
        });
        assertEquals(expected, source);
    }

    @Test
    public void reverseEmpty() {
        List<Integer> source = new ArrayList<>();
        reverse(source, 0, 0);
        List<Integer> expected = new ArrayList<>();
        assertEquals(expected, source);
    }

    @Test
    public void reverseSingleItem() {
        List<Integer> source = new ArrayList<>(new Integer[]{1});
        reverse(source, 0, 1);
        List<Integer> expected = new ArrayList<>(new Integer[]{1});
        assertEquals(expected, source);
    }

    @Test
    public void reverseEvenSize() {
        List<Integer> source = new ArrayList<>(new Integer[]{1, 2, 3, 4});
        reverse(source, 0, 4);
        List<Integer> expected = new ArrayList<>(new Integer[]{4, 3, 2, 1});
        assertEquals(expected, source);
    }

    @Test
    public void reverseOddSize() {
        List<Integer> source = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5});
        reverse(source, 0, 5);
        List<Integer> expected = new ArrayList<>(new Integer[]{5, 4, 3, 2, 1});
        assertEquals(expected, source);
    }

    @Test
    public void reverseMiddle() {
        List<Integer> source = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8});
        reverse(source, 2, 5);
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 2, 5, 4, 3, 6, 7, 8});
        assertEquals(expected, source);
    }

    @Test
    void sort() {
        List<Integer> list = new ArrayList<>(new Integer[]{
                1, 2, 3, 25, 23, 35, 4, 5, 7, 9, 13, 6, 8, 7, 6, 5, 4, 3, 2, 1
        });
        List<Integer> expected = new ArrayList<>(new Integer[]{
                1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 9, 13, 23, 25, 35
        });
        timSort(list, cmp);
        assertEquals(expected, list);
    }

    @Test
    void sortIdealCase() {
        List<Integer> list = new ArrayList<>(new Integer[]{
                9, 10, 11, 12, 13, 14, 15, 16, 5, 6, 7, 8, 3, 4, 1, 2
        });
        timSort(list, cmp);
        List<Integer> expected = new ArrayList<>();
        IntStream.iterate(1, x -> x <= 16, x -> x + 1).forEach(expected::add);
        assertEquals(expected, list);
    }

    @Test
    void sortLongRandom() {
        Integer[] objects = new Random().ints(100, -100, 100).boxed().toArray(Integer[]::new);
        List<Integer> source = new ArrayList<>(objects);
        timSort(source, cmp);
        assertSorted(source);
    }
}
