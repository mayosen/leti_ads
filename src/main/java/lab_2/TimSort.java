package lab_2;

import lab_1.ArrayList;
import lab_1.List;
import lab_1.Stack;

import java.util.Comparator;

import static lab_2.InsertionSort.insertionSort;

public class TimSort {
    private static final int GALLOP_LENGTH = 7;

    private static void log(String format, Object... args) {
        System.out.format(format + "\n", args);
    }

    public static <T> String elementsString(List<T> list, int from, int to) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = from; i < to; i++) {
            builder.append(list.get(i));
            if (i != to - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    public static <T> String elementsString(List<T> list, SubList subList) {
        return elementsString(list, subList.start, subList.end);
    }

    public static <T> String elementsString(List<T> list) {
        return elementsString(list, 0, list.size());
    }

    public static <T> void timSort(List<T> list, Comparator<T> cmp) {
        log("sorting: %s", elementsString(list));
        int minRun = getMinRun(list.size());
        log("minRun: %d", minRun);
        List<SubList> subLists = new ArrayList<>();
        int start = 0;

        while (start < list.size()) {
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

            if (decreasingRun > increasingRun) {
                reverse(list, start, maxRun);
                // log("reversed: %s", elementsString(list, start, maxRun));
            }

            if (maxRun - start < minRun) {
                maxRun = Math.min(maxRun + (minRun - (maxRun - start)), list.size());
                log("extending run to minRun: %s", elementsString(list, start, maxRun));
            }

            SubList subList = new SubList(start, maxRun);
            subLists.add(subList);
            log("new %s: %s", subList, elementsString(list, subList));
            start = maxRun;
        }

        Stack<SubList> stack = new ArrayList<>();

        for (int i = 0; i < subLists.size(); i++) {
            SubList subList = subLists.get(i);
            // log("insertionSort: %s", subList, elementsString(list, subList));
            insertionSort(list, subList.start, subList.end, cmp);
            // log("sorted: %s", elementsString(list, subList));
            stack.push(subList);
        }

        while (stack.size() >= 3) {
            SubList listX = stack.pop();
            SubList listY = stack.pop();
            SubList listZ = stack.pop();

            if (!((listZ.length > listY.length + listX.length) && (listY.length > listX.length))) {
                if (listX.length <= listZ.length) {
                    log("merging %s and %s", listY, listX);
                    merge(list, listY, listX, cmp);
                    stack.push(listZ);
                    stack.push(new SubList(listY.start, listX.end));
                } else {
                    log("merging %s and %s", listZ, listY);
                    merge(list, listZ, listY, cmp);
                    stack.push(new SubList(listZ.start, listY.end));
                    stack.push(listX);
                }
            } else {
                log("merging %s and %s", listY, listX);
                merge(list, listY, listX, cmp);
                stack.push(listZ);
                stack.push(new SubList(listY.start, listX.end));
            }
        }

        if (stack.size() == 2) {
            SubList listX = stack.pop();
            SubList listY = stack.pop();
            log("last merging %s and %s", listY, listX);
            merge(list, listY, listX, cmp);
        } else {
            log("stack.size = 1");
        }
    }

    public static int getMinRun(int n) {
        int flag = 0;
        while (n >= 64) {
            flag |= (n & 1);
            n >>= 1;
        }
        return n + flag;
    }

    static class SubList {
        private final int start;
        private final int end;
        private final int length;

        /**
         * @param start начало (включительно)
         * @param end конец (не включительно)
         */
        SubList(int start, int end) {
            this.start = start;
            this.end = end;
            this.length = end - start;
        }

        @Override
        public String toString() {
            return "SubList{" +
                    "start=" + start +
                    ", end=" + end +
                    ", length=" + length +
                    '}';
        }
    }

    public static <T> void reverse(List<T> list, int from, int to) {
        to--;
        while (from < to) {
            T temp = list.get(from);
            list.set(from, list.get(to));
            list.set(to, temp);
            from++;
            to--;
        }
    }

    /**
     * Слияние двух соседних подмассивов.
     */
    public static <T> void merge(List<T> list, SubList left, SubList right, Comparator<T> cmp) {
        List<T> leftCopy = new ArrayList<>(left.length);
        for (int i = left.start; i < left.end; i++) {
            leftCopy.add(list.get(i));
        }

        int l = left.start;
        int r = right.start;
        int current = left.start;
        int leftSeries = 0;
        int rightSeries = 0;

        while (l < left.end && r < right.end) {
            int leftIndex = l - left.start;
            if (cmp.compare(leftCopy.get(leftIndex), list.get(r)) <= 0) {
                list.set(current, leftCopy.get(leftIndex));
                // log("merged left[%d] to result[%d] = %d", l, current, leftCopy.get(leftIndex));
                current++;
                l++;

                if (rightSeries > 0) {
                    rightSeries = 0;
                    leftSeries = 1;
                } else {
                    leftSeries++;
                }

                if (leftSeries == GALLOP_LENGTH) {
                    int end = findSeriesEnd(leftCopy, leftIndex - 1, left.length, list.get(r), cmp);
                    // log("left gallop from %d to %d", l - 1, end);
                    for (; l <= end; l++, current++) {
                        leftIndex = l - left.start;
                        list.set(current, leftCopy.get(leftIndex));
                        // log("merged galloping left[%d] to result[%d] = %d", l, current, leftCopy.get(leftIndex));
                    }
                }
            } else {
                list.set(current, list.get(r));
                // log("merged right[%d] to result[%d] = %d", r, current, list.get(r));
                current++;
                r++;

                if (leftSeries > 0) {
                    leftSeries = 0;
                    rightSeries = 1;
                } else {
                    rightSeries++;
                }

                if (rightSeries == GALLOP_LENGTH) {
                    int end = findSeriesEnd(list, r - 1, right.end, leftCopy.get(leftIndex), cmp);
                    // log("right gallop from %d to %d", r - 1, end);
                    for (; r <= end; r++, current++) {
                        list.set(current, list.get(r));
                        // log("merged galloping right[%d] to result[%d] = %d", r, current, list.get(r));
                    }
                }
            }
        }

        for (int leftIndex = l - left.start; leftIndex < left.length; leftIndex++, current++) {
            list.set(current, leftCopy.get(leftIndex));
            // log("rests left[%d] to result[%d] = %d", l, current, leftCopy.get(leftIndex));
        }

        for (; r < right.end; r++, current++) {
            list.set(current, list.get(r));
            // log("rests right[%d] to result[%d] = %d", r, current, list.get(r));
        }
    }

    /**
     * Бинарный поиск индекса последнего элемента в списке list (правый бинарный поиск),
     * который не превосходит значение limitValue.
     *
     * @param list исходный список
     * @param from индекс, с которого ведется поиск (включительно)
     * @param to индекс, до которого ведется поиск (не включительно)
     * @param limitValue предельное значение
     * @param cmp компаратор
     * @return индекс последнего подходящего элемента
     */
    public static <T> int findSeriesEnd(List<T> list, int from, int to, T limitValue, Comparator<T> cmp) {
        int left = from;
        int right = to - 1;

        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (cmp.compare(list.get(mid), limitValue) <= 0) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        if (cmp.compare(list.get(right), limitValue) > 0) {
            return -1;
        }
        return right;
    }
}
