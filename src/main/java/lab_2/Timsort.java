package lab_2;

import lab_1.ArrayList;
import lab_1.Stack;

import java.util.Comparator;

import static lab_2.InsertionSort.insertionSort;

public class Timsort {
    public static <T> ArrayList<T> timsort(ArrayList<T> list, Comparator<T> cmp) {
        int minRun = getMinRun(list.size());  // TODO: Применять minRun
        ArrayList<ArrayList<T>> subLists = new ArrayList<>();
        int start = 0;

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

        for (int i = 0; i < subLists.size(); i++) {
            insertionSort(subLists.get(i), cmp);
        }

        Stack<ArrayList<T>> stack = new ArrayList<>();

        for (int i = 0; i < subLists.size(); i++) {
            ArrayList<T> subList = subLists.get(i);
            stack.push(subList);

            if (stack.size() >= 2) {
                ArrayList<T> listX = stack.pop();
                ArrayList<T> listY = stack.pop();

                if (stack.isEmpty()) {
                    if (listY.size() <= listX.size()) {
                        stack.push(merge(listX, listY, cmp));
                    } else {
                        stack.push(listY);
                        stack.push(listX);
                    }
                } else {
                    ArrayList<T> listZ = stack.pop();

                    if (listZ.size() <= listX.size() + listY.size()) {
                        if (listX.size() <= listZ.size()) {
                            stack.push(listZ);
                            stack.push(merge(listY, listX, cmp));
                        } else {
                            stack.push(merge(listY, listZ, cmp));
                            stack.push(listX);
                        }
                    } else {
                        stack.push(listZ);
                        stack.push(listY);
                        stack.push(listX);
                    }
                }
            }
        }

        while (stack.size() > 1) {
            ArrayList<T> listX = stack.pop();
            ArrayList<T> listY = stack.pop();
            stack.push(merge(listX, listY, cmp));
        }

        System.out.println(4);
        return stack.pop();
    }

    public static int getMinRun(int n) {
        int flag = 0;
        while (n >= 64) {
            flag |= (n & 1);
            n >>= 1;
        }
        return n + flag;
    }

    public static <T> ArrayList<T> merge(ArrayList<T> left, ArrayList<T> right, Comparator<T> cmp) {
        ArrayList<T> merged = new ArrayList<>(left.size() + right.size());
        int l = 0;
        int r = 0;

        while (l < left.size() && r < right.size()) {
            if (cmp.compare(left.get(l), right.get(r)) <= 0) {
                merged.add(left.get(l));
                l++;
            } else {
                merged.add(right.get(r));
                r++;
            }
        }

        while (l < left.size()) {
            merged.add(left.get(l));
            l++;
        }

        while (r < right.size()) {
            merged.add(right.get(r));
            r++;
        }

        return merged;
    }
}
