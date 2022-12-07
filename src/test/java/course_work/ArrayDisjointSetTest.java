package course_work;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayDisjointSetTest {
    private DisjointSet set;

    @BeforeEach
    void setupSet() {
        set = new ArrayDisjointSet(6);
    }

    private void assertMultipleEquals(int... values) {
        for (int i = 1; i < values.length; i++) {
            if (set.find(values[i]) != set.find(values[i - 1])) {
                throw new AssertionError();
            }
        }
    }

    @Test
    void initialValues() {
        assertEquals(0, set.find(0));
        assertEquals(1, set.find(1));
        assertEquals(2, set.find(2));
        assertEquals(3, set.find(3));
        assertEquals(4, set.find(4));
        assertEquals(5, set.find(5));
    }

    @Test
    void unions() {
        set.union(0, 1);
        assertMultipleEquals(0, 1);
        set.union(2, 3);
        assertMultipleEquals(2, 3);
        set.union(4, 5);
        assertMultipleEquals(4, 5);
        set.union(0, 5);
        assertMultipleEquals(0, 1, 4, 5);
        set.union(0, 2);
        assertMultipleEquals(0, 1, 2, 3, 4, 5);
    }

    @Test
    void sequentialUnions() {
        set.union(0, 1);
        assertMultipleEquals(0, 1);
        set.union(0, 2);
        assertMultipleEquals(0, 1, 2);
        set.union(0, 3);
        assertMultipleEquals(0, 1, 2, 3);
        set.union(0, 4);
        assertMultipleEquals(0, 1, 2, 3, 4);
        set.union(0, 5);
        assertMultipleEquals(0, 1, 2, 3, 4, 5);
    }
}
