package lab_1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListEqualsTest {
    @Test
    public void equalsEmpty() {
        List<Integer> linked = new SinglyLinkedList<>();
        List<Integer> array = new ArrayList<>();
        assertEquals(linked, array);
        assertEquals(array, linked);
    }

    @Test
    public void equals() {
        List<Integer> linked = new SinglyLinkedList<>(new Integer[]{1, 1, 1, 1, 5});
        List<Integer> array = new ArrayList<>(new Integer[]{1, 1, 1, 1, 5});
        assertEquals(linked, array);
        assertEquals(array, linked);
    }

    @Test
    public void notEqualsBySize() {
        List<Integer> linked = new SinglyLinkedList<>(new Integer[]{1, 1, 1, 1, 5});
        List<Integer> array = new ArrayList<>(new Integer[]{6});
        assertNotEquals(linked, array);
        assertNotEquals(array, linked);
    }

    @Test
    public void notEqualsByElement() {
        List<Integer> linked = new SinglyLinkedList<>(new Integer[]{1, 1, 1, 1, 5});
        List<Integer> array = new ArrayList<>(new Integer[]{1, 1, 1, 10, 5});
        assertNotEquals(linked, array);
        assertNotEquals(array, linked);
    }
}
