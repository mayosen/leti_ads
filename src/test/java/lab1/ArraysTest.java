package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArraysTest {
    @Test
    public void shiftToLeftOn1() {
        Integer[] array = {1, 2, 3, 4, 5};
        Arrays.arrayCopy(array, 1, array, 0, array.length - 1);
        Integer[] expected = {2, 3, 4, 5, 5};
        assertArrayEquals(expected, array);
    }

    @Test
    public void copySame() {
        Object[] src = {1, 2, 3, 4, 5};
        Object[] copied = Arrays.copyOf(src, src.length);
        assertArrayEquals(src, copied);
    }

    @Test
    public void copyExpanding() {
        Object[] src = {1, 2, 3, 4, 5};
        Object[] copied = Arrays.copyOf(src, src.length + 2);
        assertEquals(src.length + 2, copied.length);
        Object[] expected = {1, 2, 3, 4, 5, null, null};
        assertArrayEquals(expected, copied);
    }

    @Test
    public void copyReducing() {
        Object[] src = {1, 2, 3, 4, 5};
        Object[] copied = Arrays.copyOf(src, src.length - 2);
        assertEquals(src.length - 2, copied.length);
        Object[] expected = {1, 2, 3};
        assertArrayEquals(expected, copied);
    }
}
