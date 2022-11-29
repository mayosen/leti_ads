package lab_3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static lab_3.DepthFirstSearch.*;
import static lab_3.Parser.parse;
import static org.junit.jupiter.api.Assertions.*;

class DepthFirstSearchTest {
    static Node root;

    @BeforeAll
    static void setupRoot() {
        root = parse("(1 (2 (4) (5)) (3 (6) (7)))");
    }

    @Test
    void preOrderRecursive() {
        List<Integer> found = new ArrayList<>();
        searchPreOrderRecursive(root, node -> found.add(node.value));
        List<Integer> expected = List.of(1, 2, 4, 5, 3, 6, 7);
        assertEquals(expected, found);
    }

    @Test
    void preOrder() {
        List<Integer> found = new ArrayList<>();
        searchPreOrder(root, node -> found.add(node.value));
        List<Integer> expected = List.of(1, 2, 4, 5, 3, 6, 7);
        assertEquals(expected, found);
    }

    @Test
    void inOrder() {
        List<Integer> found = new ArrayList<>();
        searchInOrder(root, node -> found.add(node.value));
        List<Integer> expected = List.of(4, 2, 5, 1, 6, 3, 7);
        assertEquals(expected, found);
    }

    @Test
    void postOrder() {
        List<Integer> found = new ArrayList<>();
        searchPostOrder(root, node -> found.add(node.value));
        List<Integer> expected = List.of(4, 5, 2, 6, 7, 3, 1);
        assertEquals(expected, found);
    }
}
