package lab3;

import lab1.ArrayList;
import lab1.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static lab3.Parser.parse;
import static org.junit.jupiter.api.Assertions.*;

class BreadthFirstSearchTest {
    static Node root;

    @BeforeAll
    static void setupRoot() {
        root = parse("(1 (2 (4) (5)) (3 (6) (7)))");
    }

    @Test
    void search() {
        List<Integer> found = new ArrayList<>();
        BreadthFirstSearch.search(root, node -> found.add(node.value));
        List<Integer> expected = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        assertEquals(expected, found);
    }
}
