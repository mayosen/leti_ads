package lab_3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvlTreeTest {
    @Test
    void rotateLeft() {
        Node root = Parser.parse("(1 (2) (3 (4) (5)))");
        Node rotated = AvlTree.rotateLeft(root);
        assertEquals(3, rotated.value);
        assertEquals(1, rotated.left.value);
        assertEquals(2, rotated.left.left.value);
        assertEquals(4, rotated.left.right.value);
        assertEquals(5, rotated.right.value);
    }

    @Test
    void rotateRight() {
        Node root = Parser.parse("(1 (2 (4) (5)) (3))");
        Node rotated = AvlTree.rotateRight(root);
        assertEquals(2, rotated.value);
        assertEquals(4, rotated.left.value);
        assertEquals(1, rotated.right.value);
        assertEquals(5, rotated.right.left.value);
        assertEquals(3, rotated.right.right.value);
    }

    @Test
    void rotateBigLeft() {
        Node root = Parser.parse("(1 (2) (3 (4 (6) (7)) (5)))");
        Node rotated = AvlTree.rotateBigLeft(root);
        assertEquals(4, rotated.value);
        assertEquals(1, rotated.left.value);
        assertEquals(2, rotated.left.left.value);
        assertEquals(6, rotated.left.right.value);
        assertEquals(3, rotated.right.value);
        assertEquals(7, rotated.right.left.value);
        assertEquals(5, rotated.right.right.value);
    }

    @Test
    void rotateBigRight() {
        Node root = Parser.parse("(1 (2 (4) (5 (6) (7))) (3))");
        Node rotated = AvlTree.rotateBigRight(root);
        assertEquals(5, rotated.value);
        assertEquals(2, rotated.left.value);
        assertEquals(4, rotated.left.left.value);
        assertEquals(6, rotated.left.right.value);
        assertEquals(1, rotated.right.value);
        assertEquals(7, rotated.right.left.value);
        assertEquals(3, rotated.right.right.value);
    }









    // TODO: Проверить добавление с помощью BFS

    @Test
    void add() {
    }

    /*
    @Test
    void find() {
        // TODO: Передавать сбалансированное дерево
        Node root = Parser.parse("(8 (3 (1) (6 (4) (7))) (10 (null) (14 (13) (null))))");
        Tree tree = new AvlTree(root);
        assertEquals(8, tree.find(8).value);
        assertEquals(3, tree.find(3).value);
        assertEquals(1, tree.find(1).value);
        assertEquals(6, tree.find(6).value);
        assertEquals(4, tree.find(4).value);
        assertEquals(7, tree.find(7).value);
        assertEquals(10, tree.find(10).value);
        assertEquals(14, tree.find(14).value);
        assertEquals(13, tree.find(13).value);
        assertNull(tree.find(0));
        assertNull(tree.find(-100));
        assertNull(tree.find(100));
    }
    */

    @Test
    void remove() {
    }

    @Test
    void depthFirstSearch() {
    }

    @Test
    void breadthFirstSearch() {
    }
}
