package lab_1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayListTest {
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
    }

    @Test
    public void addElements() {
        List<Integer> list = new ArrayList<>(new Integer[]{1, 2 ,3});
        list.add(10);
        assertEquals(4, list.size());
        list.add(20);
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

    private List<Integer> generateSampleList() {
        return new ArrayList<>(new Integer[]{10, 5, 0, 888});
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
        List<Integer> expected = new ArrayList<>(new Integer[]{5, 0, 888});
        assertEquals(expected, list);
    }

    @Test
    public void removeMiddleByObject() {
        List<Integer> list = generateSampleList();
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
        List<Integer> list = generateSampleList();
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
        List<Integer> list = generateSampleList();
        assertTrue(list.remove(0));
        assertEquals(3, list.size());
        List<Integer> expected = new ArrayList<>(new Integer[]{5, 0, 888});
        assertEquals(expected, list);
    }

    @Test
    public void removeMiddleByIndex() {
        List<Integer> list = generateSampleList();
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
        List<Integer> list = new ArrayList<>();
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
        List<Integer> list = new ArrayList<>();
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
        List<Integer> list = generateSampleList();
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
        assertDoesNotThrow(list::sort);
    }

    @Test
    public void sortAeArrayList() {
        List<Integer> list = new ArrayList<>();
        list.add(4);
        assertDoesNotThrow(list::sort);
    }

    @Test
    public void sort2Ordered() {
        List<Integer> list = new ArrayList<>(new Integer[]{1, 10});
        list.sort();
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 10});
        assertEquals(expected, list);
    }

    @Test
    public void sort2Unordered() {
        List<Integer> list = new ArrayList<>(new Integer[]{10, 1});
        list.sort();
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 10});
        assertEquals(expected, list);
    }

    @Test
    public void sort3Unordered() {
        List<Integer> list = new ArrayList<>(new Integer[]{10, 1, 13});
        list.sort();
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 10, 13});
        assertEquals(expected, list);
    }

    @Test
    public void sort3Ordered() {
        List<Integer> list = new ArrayList<>(new Integer[]{1, 10, 13});
        list.sort();
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 10, 13});
        assertEquals(expected, list);
    }

    @Test
    public void sortWithIterations() {
        List<Integer> list = new ArrayList<>(new Integer[]{0, 14, 6, 5, 2});
        list.sort();
        List<Integer> expected = new ArrayList<>(new Integer[]{0, 2, 5, 6, 14});
        assertEquals(expected, list);
    }

    @Test
    public void sortComplex() {
        List<Integer> list = new ArrayList<>(new Integer[]{8, 1, 4, 3, 2, 100, 1});
        list.sort();
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 1, 2, 3, 4, 8, 100});
        assertEquals(expected, list);
    }

    @Test
    public void sortComplexAlreadySorted() {
        List<Integer> list = new ArrayList<>(new Integer[]{1, 1, 2, 3, 4, 8, 100});
        list.sort();
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 1, 2, 3, 4, 8, 100});
        assertEquals(expected, list);
    }
}
