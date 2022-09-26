package lab_1;

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
        Integer[] src = {1, 2, 3, 4, 5};
        Integer[] copied = Arrays.copyOf(src, src.length);
        assertArrayEquals(src, copied);
    }

    @Test
    public void copyExpanding() {
        Integer[] src = {1, 2, 3, 4, 5};
        Integer[] copied = Arrays.copyOf(src, src.length + 2);
        assertEquals(src.length + 2, copied.length);
        Integer[] expected = {1, 2, 3, 4, 5, null, null};
        assertArrayEquals(expected, copied);
    }

    @Test
    public void copyReducing() {
        Integer[] src = {1, 2, 3, 4, 5};
        Integer[] copied = Arrays.copyOf(src, src.length - 2);
        assertEquals(src.length - 2, copied.length);
        Integer[] expected = {1, 2, 3};
        assertArrayEquals(expected, copied);
    }
}
