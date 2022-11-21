package lab_3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static lab_3.BinaryTree.Node;
import static lab_3.BinaryTree.buildTree;
import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {
    private Node root;

    @BeforeEach
    void setupRoot() {
        root = new Node(null, 1);
    }

    @Test
    void buildTreeOf0() {
        buildTree(root, 0);
        assertNull(root.left);
        assertNull(root.right);
    }

    @Test
    void buildTreeOf1() {
        buildTree(root, 1);
        assertNull(root.left);
        assertNull(root.right);
    }

    @Test
    void buildTreeOf2() {
        buildTree(root, 2);
        assertEquals(2, root.left.value);
        assertNull(root.right);
    }

    @Test
    void buildTreeOf3() {
        buildTree(root, 3);
        Node left = root.left;
        Node right = root.right;
        assertEquals(2, left.value);
        assertEquals(3, right.value);
        assertNull(left.left);
        assertNull(left.right);
        assertNull(right.left);
        assertNull(right.right);
    }

    @Test
    void buildTreeOf4() {
        buildTree(root, 4);
        Node left = root.left;
        Node right = root.right;
        assertEquals(2, left.value);
        assertEquals(3, right.value);
        assertEquals(4, left.left.value);
        assertNull(left.right);
        assertNull(right.left);
        assertNull(right.right);
    }

    @Test
    void buildTreeOf5() {
        buildTree(root, 5);
        Node left = root.left;
        Node right = root.right;
        assertEquals(2, left.value);
        assertEquals(3, right.value);
        assertEquals(4, left.left.value);
        assertEquals(5, left.right.value);
        assertNull(right.left);
        assertNull(right.right);
    }
}
