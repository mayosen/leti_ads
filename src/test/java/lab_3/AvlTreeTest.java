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

    @Test
    void searchInEmptyTree() {
        Tree tree = new AvlTree();
        assertNull(tree.search(0));
    }

    @Test
    void searchExisting() {
        Node root = Parser.parse("(8 (3 (1) (6 (4) (7))) (10 (null) (14 (13) (null))))");
        Tree tree = new AvlTree(root);
        assertEquals(8, tree.search(8).value);
        assertEquals(3, tree.search(3).value);
        assertEquals(1, tree.search(1).value);
        assertEquals(4, tree.search(4).value);
        assertEquals(7, tree.search(7).value);
        assertEquals(13, tree.search(13).value);
    }

    @Test
    void searchNotExisting() {
        Node root = Parser.parse("(8 (3 (1) (6 (4) (7))) (10 (null) (14 (13) (null))))");
        Tree tree = new AvlTree(root);
        assertNull(tree.search(-1));
        assertNull(tree.search(0));
        assertNull(tree.search(100));
    }

    @Test
    void insertRoot() {
        Tree tree = new AvlTree();
        tree.insert(1);
        assertEquals(1, tree.search(1).value);
    }

    @Test
    void insertAlreadyExisting() {
        Node root = Parser.parse("(8 (3 (1) (6 (4) (7))) (10 (null) (14 (13) (null))))");
        Tree tree = new AvlTree(root);
        assertThrows(DuplicatedValueException.class, () -> tree.insert(8));
        assertThrows(DuplicatedValueException.class, () -> tree.insert(4));
        assertThrows(DuplicatedValueException.class, () -> tree.insert(7));
        assertThrows(DuplicatedValueException.class, () -> tree.insert(13));
    }

    @Test
    void insertWithRotatingLeft() {
        AvlTree tree = AvlTree.of(1, 2, 3);
        Node root = tree.getRoot();
        assertEquals(2, root.value);
        assertEquals(1, root.left.value);
        assertEquals(3, root.right.value);
    }

    @Test
    void insertWithRotatingRight() {
        AvlTree tree = AvlTree.of(3, 2, 1);
        Node root = tree.getRoot();
        assertEquals(2, root.value);
        assertEquals(1, root.left.value);
        assertEquals(3, root.right.value);
    }

    @Test
    void insertWithRotatingBigLeft() {
        AvlTree tree = AvlTree.of(10, 20, 30, 29, 31, 28);
        Node root = tree.getRoot();
        assertEquals(29, root.value);
        assertEquals(20, root.left.value);
        assertEquals(10, root.left.left.value);
        assertEquals(28, root.left.right.value);
        assertEquals(30, root.right.value);
        assertEquals(31, root.right.right.value);
    }

    @Test
    void insertWithRotatingBigRight() {
        AvlTree tree = AvlTree.of(30, 20, 10, 9, 11, 12);
        Node root = tree.getRoot();
        assertEquals(11, root.value);
        assertEquals(10, root.left.value);
        assertEquals(9, root.left.left.value);
        assertEquals(20, root.right.value);
        assertEquals(12, root.right.left.value);
        assertEquals(30, root.right.right.value);
    }

    @Test
    void deleteFromEmptyTree() {
        AvlTree tree = new AvlTree();
        assertNull(tree.getRoot());
    }

    @Test
    void deleteNotExisting() {
        AvlTree tree = AvlTree.of(30, 20, 10);
        Node root = tree.getRoot();
        assertEquals(20, root.value);
        assertEquals(10, root.left.value);
        assertEquals(30, root.right.value);
    }

    @Test
    void deleteSingleRoot() {
        AvlTree tree = AvlTree.of(30);
        tree.delete(30);
        assertNull(tree.getRoot());
    }

    @Test
    void deleteRoot() {
        AvlTree tree = AvlTree.of(30, 20, 10);
        tree.delete(20);
        Node root = tree.getRoot();
        assertEquals(30, root.value);
        assertEquals(10, root.left.value);
    }

    @Test
    void deleteInSimpleCase() {
        AvlTree tree = AvlTree.of(30, 20, 10, 9, 11, 12, 21);
        tree.delete(20);
        Node root = tree.getRoot();
        assertEquals(11, root.value);
        assertEquals(10, root.left.value);
        assertEquals(9, root.left.left.value);
        assertEquals(21, root.right.value);
        assertEquals(12, root.right.left.value);
        assertEquals(30, root.right.right.value);
    }
    @Test
    void deleteWithBalancing() {
        AvlTree tree = AvlTree.of(30, 20, 10, 9, 11, 12, 21);
        tree.delete(10);  // left rotation
        Node root = tree.getRoot();
        assertEquals(20, root.value);
        assertEquals(11, root.left.value);
        assertEquals(9, root.left.left.value);
        assertEquals(12, root.left.right.value);
        assertEquals(30, root.right.value);
        assertEquals(21, root.right.left.value);
    }
}
