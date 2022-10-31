package lab_2;

import lab_1.ArrayList;
import org.junit.jupiter.api.Test;

import static lab_2.Timsort.getMinRun;
import static lab_2.Timsort.timsort;
import static org.junit.jupiter.api.Assertions.*;

class TimsortTest {
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
        timsort(list, Integer::compare);
    }
}