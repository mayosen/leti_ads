package lab_2;

import lab_1.ArrayList;
import lab_1.List;
import lab_1.Stack;

import java.util.Comparator;

import static lab_2.InsertionSort.insertionSort;

public class TimSort {
    private static final int GALLOP_LENGTH = 7;

    public static <T> void timSort(List<T> list, Comparator<T> cmp) {
        Stack<SubList> stack = new ArrayList<>();
        int minRun = getMinRun(list.size());
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
            }

            if (maxRun - start < minRun) {
                maxRun = Math.min(start + minRun, list.size());
            }

            SubList subList = new SubList(start, maxRun);
            insertionSort(list, subList.start, subList.end, cmp);

            stack.push(subList);
            mergeCollapse(list, stack, cmp);

            start = maxRun;
        }

        mergeForceCollapse(list, stack, cmp);
    }

    static int getMinRun(int n) {
        int flag = 0;
        while (n >= 64) {
            flag |= (n & 1);
            n >>= 1;
        }
        return n + flag;
    }

    /**
     * Класс, описывающий границы подмассива.
     */
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

    /**
     * Разворот элементов подмассива.
     *
     * @param list список
     * @param from индекс, начиная с которого разворачивать (включительно)
     * @param to индекс, до которого разворачивать (не включительно)
     */
    static <T> void reverse(List<T> list, int from, int to) {
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
     * Слияние двух подмассивов, если они не выполняют инвариант.
     */
    static <T> void mergeCollapse(List<T> list, Stack<SubList> stack, Comparator<T> cmp) {
        while (stack.size() >= 2) {
            SubList listX = stack.pop();
            SubList listY = stack.pop();

            if (stack.isEmpty()) {
                if (!(listY.length > listX.length)) {
                    merge(list, listY, listX, cmp);
                    stack.push(new SubList(listY.start, listX.end));
                } else {
                    stack.push(listY);
                    stack.push(listX);
                }
                break;
            } else {
                SubList listZ = stack.pop();

                if (!((listZ.length > listX.length + listY.length) && (listY.length > listX.length))) {
                    if (listX.length < listZ.length) {
                        merge(list, listY, listX, cmp);
                        stack.push(listZ);
                        stack.push(new SubList(listY.start, listX.end));
                    } else {
                        merge(list, listZ, listY, cmp);
                        stack.push(new SubList(listZ.start, listY.end));
                        stack.push(listX);
                    }
                } else {
                    stack.push(listZ);
                    stack.push(listY);
                    stack.push(listX);
                    break;
                }
            }
        }
    }

    static <T> void mergeForceCollapse(List<T> list, Stack<SubList> stack, Comparator<T> cmp) {
        while (stack.size() >= 2) {
            SubList listX = stack.pop();
            SubList listY = stack.pop();
            merge(list, listY, listX, cmp);
            stack.push(new SubList(listY.start, listX.end));
        }
    }

    /**
     * Слияние двух соседних подмассивов.
     */
    static <T> void merge(List<T> list, SubList left, SubList right, Comparator<T> cmp) {
        List<T> leftCopy = list.subList(left.start, left.end);
        int l = left.start;
        int r = right.start;
        int current = left.start;
        int leftSeries = 0;
        int rightSeries = 0;

        while (l < left.end && r < right.end) {
            T leftItem = leftCopy.get(l - left.start);
            T rightItem = list.get(r);

            if (cmp.compare(leftItem, rightItem) <= 0) {
                list.set(current, leftItem);
                current++;
                l++;

                if (rightSeries > 0) {
                    rightSeries = 0;
                    leftSeries = 1;
                } else {
                    leftSeries++;
                }

                if (leftSeries == GALLOP_LENGTH) {
                    int end = findSeriesEnd(leftCopy, l - left.start - 1, left.length, rightItem, cmp);
                    for (; l <= left.start + end; l++, current++) {
                        list.set(current, leftCopy.get(l - left.start));
                    }
                }
            } else {
                list.set(current, rightItem);
                current++;
                r++;

                if (leftSeries > 0) {
                    leftSeries = 0;
                    rightSeries = 1;
                } else {
                    rightSeries++;
                }

                if (rightSeries == GALLOP_LENGTH) {
                    int end = findSeriesEnd(list, r - 1, right.end, leftItem, cmp);
                    for (; r <= end; r++, current++) {
                        list.set(current, list.get(r));
                    }
                }
            }
        }

        for (int leftIndex = l - left.start; leftIndex < left.length; leftIndex++, current++) {
            list.set(current, leftCopy.get(leftIndex));
        }

        for (; r < right.end; r++, current++) {
            list.set(current, list.get(r));
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
    static <T> int findSeriesEnd(List<T> list, int from, int to, T limitValue, Comparator<T> cmp) {
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
