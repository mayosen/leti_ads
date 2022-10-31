package lab_2;

import lab_1.List;

import java.util.Comparator;

public class Timsort {
    public static int getMinrun(int n) {
        int r = 0;
        while (n >= 64) {
            r |= (n & 1);
            n >>= 1;
        }

        return n + r;
    }

    public static <T> void timsort(List<T> list, Comparator<T> comparator) {

    }
}
