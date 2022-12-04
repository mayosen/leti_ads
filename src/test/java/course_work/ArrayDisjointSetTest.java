package course_work;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayDisjointSetTest {
    @Test
    void test() {
        DisjointSet set = new ArrayDisjointSet(5);
        for (int i = 0; i < 5; i++) {
            set.makeSet(i);
        }
        assertEquals(1, set.find(1));
    }
}