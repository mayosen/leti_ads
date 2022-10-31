package lab_2;

import lab_1.ArrayList;
import lab_1.List;

import java.util.Comparator;
import java.util.logging.Logger;

public class Timsort {
    private static final Logger logger = Logger.getLogger(Timsort.class.getName());

    public static int getMinRun(int n) {
        int flag = 0;
        while (n >= 64) {
            flag |= (n & 1);
            n >>= 1;
        }
        return n + flag;
    }

    public static <T> void timsort(ArrayList<T> list, Comparator<T> cmp) {
        int minRun = getMinRun(list.size());
        int start = 0;
        List<List<T>> subLists = new ArrayList<>();

        while (start < list.size() - 1) {
            int current = start;

            while (current + 1 < list.size() && cmp.compare(list.get(current), list.get(current + 1)) <= 0) {
                current++;
            }

            int increasingRun = current + 1;

            current = start;

            while (current + 1 < list.size() && cmp.compare(list.get(current), list.get(current + 1)) > 0) {
                current++;
            }

            int decreasingRun = current + 1;

            int maxRun = Math.max(increasingRun, decreasingRun);
            ArrayList<T> subList = list.subList(start, maxRun);
            subLists.add(subList);

            if (decreasingRun > increasingRun) {
                subList.reverse();
            }

            start = maxRun;
        }

        System.out.println(4);
    }
}
