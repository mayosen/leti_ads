package lab_1;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class SinglyLinkedListTest {
    @Test
    public void createIntegerList() {
        List<Integer> list = new SinglyLinkedList<>();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());

        list.add(2);
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());

        list.add(3);
        assertFalse(list.isEmpty());
        assertEquals(2, list.size());
    }

    @Test
    public void createFromArray() {
        List<Integer> list = new SinglyLinkedList<>(new Integer[]{1, 2, 3});
        assertEquals(3, list.size());
    }

    private List<Integer> generateSampleList() {
        return new SinglyLinkedList<>(new Integer[]{10, 5, 0, 888});
    }

    @Test
    public void removeByNotExistingObject() {
        List<Integer> list = generateSampleList();
        assertFalse(list.remove(Integer.valueOf(3)));
        assertEquals(4, list.size());
    }

    @Test
    public void removeHeadByObject() {
        List<Integer> list = generateSampleList();
        assertTrue(list.remove(Integer.valueOf(10)));
        assertEquals(3, list.size());
        List<Integer> expected = new SinglyLinkedList<>(new Integer[]{5, 0, 888});
        assertEquals(expected, list);
    }

    @Test
    public void removeMiddleByObject() {
        List<Integer> list = generateSampleList();
        assertTrue(list.remove(Integer.valueOf(0)));
        assertEquals(3, list.size());
        List<Integer> expected = new SinglyLinkedList<>(new Integer[]{10, 5, 888});
        assertEquals(expected, list);
    }

    @Test
    public void removeFromEmptyList() {
        List<Integer> list = new SinglyLinkedList<>();
        assertFalse(list.remove(Integer.valueOf(0)));
        assertEquals(0, list.size());
    }

    @Test
    public void removeByInvalidIndex() {
        List<Integer> list = generateSampleList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(4));
    }

    @Test
    public void removeByIndexFromEmptyList() {
        List<Integer> list = new SinglyLinkedList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    @Test
    public void removeHeadByIndex() {
        List<Integer> list = generateSampleList();
        assertTrue(list.remove(0));
        assertEquals(3, list.size());
        List<Integer> expected = new SinglyLinkedList<>(new Integer[]{5, 0, 888});
        assertEquals(expected, list);
    }

    @Test
    public void removeMiddleByIndex() {
        List<Integer> list = generateSampleList();
        assertTrue(list.remove(2));
        assertEquals(3, list.size());
        List<Integer> expected = new SinglyLinkedList<>(new Integer[]{10, 5, 888});
        assertEquals(expected, list);
    }

    @Test
    public void getFromEmpty() {
        List<Integer> list = new SinglyLinkedList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    public void getByInvalidIndex() {
        List<Integer> list = generateSampleList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(4));
    }

    @Test
    public void getHead() {
        List<Integer> list = generateSampleList();
        assertEquals(10, list.get(0).intValue());
    }

    @Test
    public void getMiddle() {
        List<Integer> list = generateSampleList();
        assertEquals(888, list.get(3).intValue());
    }

    @Test
    public void setByInvalidIndex() {
        List<Integer> list = generateSampleList();
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(4, 1000));
    }

    @Test
    public void setHead() {
        List<Integer> list = generateSampleList();
        assertEquals(10, list.set(0, 228).intValue());
        assertEquals(228, list.get(0).intValue());
    }

    @Test
    public void setMiddle() {
        List<Integer> list = generateSampleList();
        assertEquals(0, list.set(2, 228).intValue());
        assertEquals(228, list.get(2).intValue());
    }

    @Test
    public void emptyListContains() {
        List<Integer> list = new SinglyLinkedList<>();
        assertFalse(list.contains(Integer.valueOf(1)));
    }

    @Test
    public void listNotContains() {
        List<Integer> list = generateSampleList();
        assertFalse(list.contains(Integer.valueOf(111)));
    }

    @Test
    public void listContainsHead() {
        List<Integer> list = generateSampleList();
        assertTrue(list.contains(Integer.valueOf(10)));
    }

    @Test
    public void listContainsMiddle() {
        List<Integer> list = generateSampleList();
        assertTrue(list.contains(Integer.valueOf(0)));
    }

    @Test
    public void indexFromEmptyList() {
        List<Integer> list = new SinglyLinkedList<>();
        assertEquals(-1, list.indexOf(Integer.valueOf(1)));
    }

    @Test
    public void indexOfHead() {
        List<Integer> list = generateSampleList();
        assertEquals(0, list.indexOf(Integer.valueOf(10)));
    }

    @Test
    public void indexOfMiddle() {
        List<Integer> list = generateSampleList();
        assertEquals(3, list.indexOf(Integer.valueOf(888)));
    }

    @Test
    public void clearEmptyList() {
        List<Integer> list = new SinglyLinkedList<>();
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void clearListWithSingle() {
        List<Integer> list = new SinglyLinkedList<>();
        list.add(12);
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void clearSampleList() {
        List<Integer> list = generateSampleList();
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void equalsEmptyToSelf() {
        List<Integer> list = new SinglyLinkedList<>();
        assertEquals(list, list);
    }

    @Test
    public void equalsToSelf() {
        List<Integer> list = generateSampleList();
        assertEquals(list, list);
    }

    @Test
    public void notEqualsToNull() {
        List<Integer> list = generateSampleList();
        assertNotEquals(list, null);
    }

    @Test
    public void notEqualsToDifferentSizeEmpty() {
        List<Integer> list = new SinglyLinkedList<>(new Integer[]{1, 2, 3});
        List<Integer> other = new SinglyLinkedList<>(new Integer[]{});
        assertNotEquals(list, other);
    }

    @Test
    public void notEqualsToDifferentSize() {
        List<Integer> list = new SinglyLinkedList<>(new Integer[]{1, 2, 3});
        List<Integer> other = new SinglyLinkedList<>(new Integer[]{10, 10, 10, 10, 10});
        assertNotEquals(list, other);
    }

    @Test
    public void notEqualsToDifferentList() {
        List<Integer> list = new SinglyLinkedList<>(new Integer[]{11, 12, 13, 14, 15});
        List<Integer> other = new SinglyLinkedList<>(new Integer[]{11, 12, 13, 13, 15});
        assertNotEquals(list, other);
    }

    @Test
    public void equalsToSameList() {
        List<Integer> list = new SinglyLinkedList<>(new Integer[]{11, 12, 13, 14, 15});
        List<Integer> other = new SinglyLinkedList<>(new Integer[]{11, 12, 13, 14, 15});
        assertEquals(list, other);
    }

    Comparator<Integer> comparator = Integer::compare;

    @Test
    public void sortEmpty() {
        List<Integer> list = new SinglyLinkedList<>();
        assertDoesNotThrow(() -> list.sort(comparator));
    }

    @Test
    public void sortSingle() {
        List<Integer> list = new SinglyLinkedList<>();
        list.add(4);
        assertDoesNotThrow(() -> list.sort(comparator));
    }

    @Test
    public void sort2Ordered() {
        List<Integer> list = new SinglyLinkedList<>(new Integer[]{1, 10});
        list.sort(comparator);
        List<Integer> expected = new SinglyLinkedList<>(new Integer[]{1, 10});
        assertEquals(expected, list);
    }

    @Test
    public void sort2Unordered() {
        List<Integer> list = new SinglyLinkedList<>(new Integer[]{10, 1});
        list.sort(comparator);
        List<Integer> expected = new SinglyLinkedList<>(new Integer[]{1, 10});
        assertEquals(expected, list);
    }

    @Test
    public void sort3Unordered() {
        List<Integer> list = new SinglyLinkedList<>(new Integer[]{10, 1, 13});
        list.sort(comparator);
        List<Integer> expected = new SinglyLinkedList<>(new Integer[]{1, 10, 13});
        assertEquals(expected, list);
    }

    @Test
    public void sort3Ordered() {
        List<Integer> list = new SinglyLinkedList<>(new Integer[]{1, 10, 13});
        list.sort(comparator);
        List<Integer> expected = new SinglyLinkedList<>(new Integer[]{1, 10, 13});
        assertEquals(expected, list);
    }

    @Test
    public void sortWithIterations() {
        List<Integer> list = new SinglyLinkedList<>(new Integer[]{0, 14, 6, 5, 2});
        list.sort(comparator);
        List<Integer> expected = new SinglyLinkedList<>(new Integer[]{0, 2, 5, 6, 14});
        assertEquals(expected, list);
    }

    @Test
    public void sortComplex() {
        List<Integer> list = new SinglyLinkedList<>(new Integer[]{8, 1, 4, 3, 2, 100, 1});
        list.sort(comparator);
        List<Integer> expected = new SinglyLinkedList<>(new Integer[]{1, 1, 2, 3, 4, 8, 100});
        assertEquals(expected, list);
    }

    @Test
    public void sortComplexAlreadySorted() {
        List<Integer> list = new SinglyLinkedList<>(new Integer[]{1, 1, 2, 3, 4, 8, 100});
        list.sort(comparator);
        List<Integer> expected = new SinglyLinkedList<>(new Integer[]{1, 1, 2, 3, 4, 8, 100});
        assertEquals(expected, list);
    }

    @Test
    public void insertToInvalidIndex() {
        List<Integer> list = new SinglyLinkedList<>(new Integer[]{10, 20, 30, 40, 50});
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(17, 0));
    }

    @Test
    public void insertToHead() {
        List<Integer> list = new SinglyLinkedList<>(new Integer[]{10, 20, 30, 40, 50});
        list.add(0, 0);
        List<Integer> expected = new SinglyLinkedList<>(new Integer[]{0, 10, 20, 30, 40, 50});
        assertEquals(expected, list);
    }

    @Test
    public void insertToMiddle() {
        List<Integer> list = new SinglyLinkedList<>(new Integer[]{10, 20, 30, 40, 50});
        list.add(2, 0);
        List<Integer> expected = new SinglyLinkedList<>(new Integer[]{10, 20, 0, 30, 40, 50});
        assertEquals(expected, list);
    }

    @Test
    public void insertToLast() {
        List<Integer> list = new SinglyLinkedList<>(new Integer[]{10, 20, 30, 40, 50});
        list.add(4, 0);
        List<Integer> expected = new SinglyLinkedList<>(new Integer[]{10, 20, 30, 40, 0, 50});
        assertEquals(expected, list);
    }

    @Test
    public void appendToEnd() {
        List<Integer> list = new SinglyLinkedList<>(new Integer[]{10, 20, 30, 40, 50});
        list.add(5, 0);
        List<Integer> expected = new SinglyLinkedList<>(new Integer[]{10, 20, 30, 40, 50, 0});
        assertEquals(expected, list);
    }
}
