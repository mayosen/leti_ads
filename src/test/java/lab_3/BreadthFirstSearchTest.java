package lab_3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static lab_3.Parser.parse;
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
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6, 7);
        assertEquals(expected, found);
    }
}
