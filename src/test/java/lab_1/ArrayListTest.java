package lab_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayListTest {
    private final Comparator<Integer> comparator = Integer::compare;
    private List<Integer> sampleList;

    @BeforeEach
    public void generateSampleList() {
        sampleList = new ArrayList<>(new Integer[]{10, 5, 0, 888});
    }

    @Test
    public void createEmpty() {
        List<Integer> list = new ArrayList<>();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, Integer.valueOf(14)));
    }

    @Test
    public void createFromArray() {
        List<Integer> list = new ArrayList<>(new Integer[]{10, 15, 20});
        assertFalse(list.isEmpty());
        assertEquals(3, list.size());
        assertTrue(list.contains(10));
        assertTrue(list.contains(15));
        assertTrue(list.contains(20));
    }

    @Test
    public void addElements() {
        List<Integer> list = new ArrayList<>(new Integer[]{1, 2 ,3});
        list.add(10);
        assertTrue(list.contains(10));
        assertEquals(4, list.size());
        list.add(20);
        assertTrue(list.contains(20));
        assertEquals(5, list.size());
    }

    @Test
    public void addGreaterThanDefaultCapacityCountOfElements() {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        assertEquals(100, list.size());
    }

    @Test
    public void removeByNotExistingObject() {
        List<Integer> list = sampleList;
        assertFalse(list.remove(Integer.valueOf(3)));
        assertEquals(4, list.size());
    }

    @Test
    public void removeHeadByObject() {
        List<Integer> list = sampleList;
        assertTrue(list.remove(Integer.valueOf(10)));
        assertEquals(3, list.size());
        List<Integer> expected = new ArrayList<>(new Integer[]{5, 0, 888});
        assertEquals(expected, list);
    }

    @Test
    public void removeMiddleByObject() {
        List<Integer> list = sampleList;
        assertTrue(list.remove(Integer.valueOf(0)));
        assertEquals(3, list.size());
        List<Integer> expected = new ArrayList<>(new Integer[]{10, 5, 888});
        assertEquals(expected, list);
    }

    @Test
    public void removeFromEmptyList() {
        List<Integer> list = new ArrayList<>();
        assertFalse(list.remove(Integer.valueOf(0)));
        assertEquals(0, list.size());
    }

    @Test
    public void removeByInvalidIndex() {
        List<Integer> list = sampleList;
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(4));
    }

    @Test
    public void removeByIndexFromEmptyList() {
        List<Integer> list = new ArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    @Test
    public void removeHeadByIndex() {
        List<Integer> list = sampleList;
        assertTrue(list.remove(0));
        assertEquals(3, list.size());
        List<Integer> expected = new ArrayList<>(new Integer[]{5, 0, 888});
        assertEquals(expected, list);
    }

    @Test
    public void removeMiddleByIndex() {
        List<Integer> list = sampleList;
        assertTrue(list.remove(2));
        assertEquals(3, list.size());
        List<Integer> expected = new ArrayList<>(new Integer[]{10, 5, 888});
        assertEquals(expected, list);
    }

    @Test
    public void getFromEmpty() {
        List<Integer> list = new ArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    public void getByInvalidIndex() {
        List<Integer> list = sampleList;
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(4));
    }

    @Test
    public void getHead() {
        List<Integer> list = sampleList;
        assertEquals(10, list.get(0).intValue());
    }

    @Test
    public void getMiddle() {
        List<Integer> list = sampleList;
        assertEquals(888, list.get(3).intValue());
    }

    @Test
    public void setByInvalidIndex() {
        List<Integer> list = sampleList;
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(4, 1000));
    }

    @Test
    public void setHead() {
        List<Integer> list = sampleList;
        assertEquals(10, list.set(0, 228).intValue());
        assertEquals(228, list.get(0).intValue());
    }

    @Test
    public void setMiddle() {
        List<Integer> list = sampleList;
        assertEquals(0, list.set(2, 228).intValue());
        assertEquals(228, list.get(2).intValue());
    }

    @Test
    public void emptyListContains() {
        List<Integer> list = new ArrayList<>();
        assertFalse(list.contains(Integer.valueOf(1)));
    }

    @Test
    public void listNotContains() {
        List<Integer> list = sampleList;
        assertFalse(list.contains(Integer.valueOf(111)));
    }

    @Test
    public void listContainsHead() {
        List<Integer> list = sampleList;
        assertTrue(list.contains(Integer.valueOf(10)));
    }

    @Test
    public void listContainsMiddle() {
        List<Integer> list = sampleList;
        assertTrue(list.contains(Integer.valueOf(0)));
    }

    @Test
    public void indexFromEmptyList() {
        List<Integer> list = new ArrayList<>();
        assertEquals(-1, list.indexOf(Integer.valueOf(1)));
    }

    @Test
    public void indexOfHead() {
        List<Integer> list = sampleList;
        assertEquals(0, list.indexOf(Integer.valueOf(10)));
    }

    @Test
    public void indexOfMiddle() {
        List<Integer> list = sampleList;
        assertEquals(3, list.indexOf(Integer.valueOf(888)));
    }

    @Test
    public void clearEmptyList() {
        List<Integer> list = new ArrayList<>();
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void clearListWithAeArrayList() {
        List<Integer> list = new ArrayList<>();
        list.add(12);
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void clearSampleList() {
        List<Integer> list = sampleList;
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void equalsEmptyToSelf() {
        List<Integer> list = new ArrayList<>();
        assertEquals(list, list);
    }

    @Test
    public void equalsToSelf() {
        List<Integer> list = sampleList;
        assertEquals(list, list);
    }

    @Test
    public void notEqualsToNull() {
        List<Integer> list = sampleList;
        assertNotEquals(list, null);
    }

    @Test
    public void notEqualsToDifferentSizeEmpty() {
        List<Integer> list = new ArrayList<>(new Integer[]{1, 2, 3});
        List<Integer> other = new ArrayList<>(new Integer[]{});
        assertNotEquals(list, other);
    }

    @Test
    public void notEqualsToDifferentSize() {
        List<Integer> list = new ArrayList<>(new Integer[]{1, 2, 3});
        List<Integer> other = new ArrayList<>(new Integer[]{10, 10, 10, 10, 10});
        assertNotEquals(list, other);
    }

    @Test
    public void notEqualsToDifferentList() {
        List<Integer> list = new ArrayList<>(new Integer[]{11, 12, 13, 14, 15});
        List<Integer> other = new ArrayList<>(new Integer[]{11, 12, 13, 13, 15});
        assertNotEquals(list, other);
    }

    @Test
    public void equalsToSameList() {
        List<Integer> list = new ArrayList<>(new Integer[]{11, 12, 13, 14, 15});
        List<Integer> other = new ArrayList<>(new Integer[]{11, 12, 13, 14, 15});
        assertEquals(list, other);
    }

    @Test
    public void sortEmpty() {
        List<Integer> list = new ArrayList<>();
        assertDoesNotThrow(() -> list.sort(comparator));
    }

    @Test
    public void sortSingle() {
        List<Integer> list = new ArrayList<>();
        list.add(4);
        assertDoesNotThrow(() -> list.sort(comparator));
    }

    @Test
    public void sort2Ordered() {
        List<Integer> list = new ArrayList<>(new Integer[]{1, 10});
        list.sort(comparator);
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 10});
        assertEquals(expected, list);
    }

    @Test
    public void sort2Unordered() {
        List<Integer> list = new ArrayList<>(new Integer[]{10, 1});
        list.sort(comparator);
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 10});
        assertEquals(expected, list);
    }

    @Test
    public void sort3Unordered() {
        List<Integer> list = new ArrayList<>(new Integer[]{10, 1, 13});
        list.sort(comparator);
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 10, 13});
        assertEquals(expected, list);
    }

    @Test
    public void sort3Ordered() {
        List<Integer> list = new ArrayList<>(new Integer[]{1, 10, 13});
        list.sort(comparator);
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 10, 13});
        assertEquals(expected, list);
    }

    @Test
    public void sortWithIterations() {
        List<Integer> list = new ArrayList<>(new Integer[]{0, 14, 6, 5, 2});
        list.sort(comparator);
        List<Integer> expected = new ArrayList<>(new Integer[]{0, 2, 5, 6, 14});
        assertEquals(expected, list);
    }

    @Test
    public void sortComplex() {
        List<Integer> list = new ArrayList<>(new Integer[]{8, 1, 4, 3, 2, 100, 1});
        list.sort(comparator);
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 1, 2, 3, 4, 8, 100});
        assertEquals(expected, list);
    }

    @Test
    public void sortComplexAlreadySorted() {
        List<Integer> list = new ArrayList<>(new Integer[]{1, 1, 2, 3, 4, 8, 100});
        list.sort(comparator);
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 1, 2, 3, 4, 8, 100});
        assertEquals(expected, list);
    }

    @Test
    public void insertToInvalidIndex() {
        List<Integer> list = new ArrayList<>(new Integer[]{10, 20, 30, 40, 50});
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(17, 0));
    }

    @Test
    public void insertToHead() {
        List<Integer> list = new ArrayList<>(new Integer[]{10, 20, 30, 40, 50});
        list.add(0, 0);
        List<Integer> expected = new ArrayList<>(new Integer[]{0, 10, 20, 30, 40, 50});
        assertEquals(expected, list);
    }

    @Test
    public void insertToMiddle() {
        List<Integer> list = new ArrayList<>(new Integer[]{10, 20, 30, 40, 50});
        list.add(2, 0);
        List<Integer> expected = new ArrayList<>(new Integer[]{10, 20, 0, 30, 40, 50});
        assertEquals(expected, list);
    }

    @Test
    public void insertToLast() {
        List<Integer> list = new ArrayList<>(new Integer[]{10, 20, 30, 40, 50});
        list.add(4, 0);
        List<Integer> expected = new ArrayList<>(new Integer[]{10, 20, 30, 40, 0, 50});
        assertEquals(expected, list);
    }

    @Test
    public void appendToEnd() {
        List<Integer> list = new ArrayList<>(new Integer[]{10, 20, 30, 40, 50});
        list.add(5, 0);
        List<Integer> expected = new ArrayList<>(new Integer[]{10, 20, 30, 40, 50, 0});
        assertEquals(expected, list);
    }

    @Test
    public void createWithInvalidCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new ArrayList<>(-1));
    }

    @Test
    public void subListEmpty() {
        List<Integer> source = new ArrayList<>(new Integer[]{10, 20, 30, 40, 50});
        List<Integer> copy = source.subList(0, 0);
        List<Integer> expected = new ArrayList<>();
        assertEquals(expected, copy);
    }

    @Test
    public void subListSingle() {
        List<Integer> source = new ArrayList<>(new Integer[]{10, 20, 30, 40, 50});
        List<Integer> copy = source.subList(0, 1);
        List<Integer> expected = new ArrayList<>(new Integer[]{10});
        assertEquals(expected, copy);
    }

    @Test
    public void subListFull() {
        List<Integer> source = new ArrayList<>(new Integer[]{10, 20, 30, 40, 50});
        List<Integer> copy = source.subList(0, source.size());
        assertEquals(source, copy);
    }

    @Test
    public void subListStarts() {
        List<Integer> source = new ArrayList<>(new Integer[]{10, 20, 30, 40, 50});
        List<Integer> copy = source.subList(0, 3);
        List<Integer> expected = new ArrayList<>(new Integer[]{10, 20, 30});
        assertEquals(expected, copy);
    }

    @Test
    public void subListEnds() {
        List<Integer> source = new ArrayList<>(new Integer[]{10, 20, 30, 40, 50});
        List<Integer> copy = source.subList(2, source.size());
        List<Integer> expected = new ArrayList<>(new Integer[]{30, 40, 50});
        assertEquals(expected, copy);
    }

    @Test
    public void subListMiddle() {
        List<Integer> source = new ArrayList<>(new Integer[]{10, 20, 30, 40, 50});
        List<Integer> copy = source.subList(2, 3);
        List<Integer> expected = new ArrayList<>(new Integer[]{30});
        assertEquals(expected, copy);
    }

    @Test
    public void reverseEmpty() {
        ArrayList<Integer> source = new ArrayList<>();
        source.reverse();
        ArrayList<Integer> expected = new ArrayList<>();
        assertEquals(expected, source);
    }

    @Test
    public void reverseSingleItem() {
        ArrayList<Integer> source = new ArrayList<>(new Integer[]{1});
        source.reverse();
        ArrayList<Integer> expected = new ArrayList<>(new Integer[]{1});
        assertEquals(expected, source);
    }

    @Test
    public void reverseEvenSize() {
        ArrayList<Integer> source = new ArrayList<>(new Integer[]{1, 2, 3, 4});
        source.reverse();
        ArrayList<Integer> expected = new ArrayList<>(new Integer[]{4, 3, 2, 1});
        assertEquals(expected, source);
    }

    @Test
    public void reverseOddSize() {
        ArrayList<Integer> source = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5});
        source.reverse();
        ArrayList<Integer> expected = new ArrayList<>(new Integer[]{5, 4, 3, 2, 1});
        assertEquals(expected, source);
    }

    @Test
    public void createStackEmpty() {
        Stack<Integer> stack = new ArrayList<>();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        assertThrows(EmptyStackException.class, stack::peek);
        assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    public void createStack() {
        Stack<Integer> stack = new ArrayList<>();
        Integer value = 14;

        assertSame(value, stack.push(value));
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
        assertSame(value, stack.peek());
        assertEquals(1, stack.size());

        assertSame(value, stack.pop());
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        assertThrows(EmptyStackException.class, stack::peek);
        assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    public void createStackFromArray() {
        Integer first = 100;
        Integer second = 200;
        Integer third = 300;
        Stack<Integer> stack = new ArrayList<>(new Integer[]{third, second, first});
        assertEquals(3, stack.size());

        assertSame(first, stack.pop());
        assertEquals(2, stack.size());
        assertSame(second, stack.pop());
        assertEquals(1, stack.size());
        assertSame(third, stack.pop());
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    public void stackSeveralPushes() {
        Integer first = 100;
        Integer second = 200;
        Integer third = 300;
        Stack<Integer> stack = new ArrayList<>();

        assertSame(first, stack.push(first));
        assertSame(second, stack.push(second));
        assertSame(third, stack.push(third));

        assertSame(third, stack.peek());
        assertSame(third, stack.pop());
        assertSame(second, stack.pop());
        assertSame(first, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void stackManyPushesWithArrayExtending() {
        Stack<Integer> stack = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            stack.push(Integer.valueOf(i));
        }

        for (int i = 100; i >= 1; i--) {
            assertEquals(i, stack.pop());
        }

        assertTrue(stack.isEmpty());
    }
}
