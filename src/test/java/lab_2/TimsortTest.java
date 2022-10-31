package lab_2;

import org.junit.jupiter.api.Test;

import static lab_2.Timsort.getMinrun;
import static org.junit.jupiter.api.Assertions.*;

class TimsortTest {
    private void printMinrun(int value) {
        System.out.format("minrun for %d = %d\n", value, getMinrun(value));
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
        printMinrun(1_000);
        printMinrun(1_000_000);
        printMinrun(10_000_000);
        printMinrun(100_000_000);
        printMinrun(1_000_000_000);
    }
}