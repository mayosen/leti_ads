package lab_3;

import org.junit.jupiter.api.Test;

import static lab_3.Parser.parse;
import static lab_3.BinaryTree.Node;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void parseTop() {
        String input = "(8)";
        Node root = parse(input);
        assertEquals(8, root.value);
        assertNull(root.left);
        assertNull(root.right);
    }

    @Test
    void parseLeft() {
        String input = "(8 (900) null)";
        Node root = parse(input);
        assertEquals(8, root.value);
        assertEquals(900, root.left.value);
        assertNull(root.left.left);
        assertNull(root.left.right);
        assertNull(root.right);
    }

    @Test
    void parseRight() {
        String input = "(8 null (1))";
        Node root = parse(input);
        assertEquals(8, root.value);
        assertNull(root.left);
        assertEquals(1, root.right.value);
        assertNull(root.right.left);
        assertNull(root.right.right);
    }

    @Test
    void parseBothSides() {
        String input = "(8 (900) (1))";
        Node root = parse(input);
        assertEquals(8, root.value);
        assertEquals(900, root.left.value);
        assertNull(root.left.left);
        assertNull(root.left.right);
        assertEquals(1, root.right.value);
        assertNull(root.right.left);
        assertNull(root.right.right);
    }

    @Test
    void parseExample() {
        String input = "(8 (9 (51) null) (1))";
        Node root = parse(input);
        assertEquals(8, root.value);
        assertEquals(9, root.left.value);
        assertEquals(51, root.left.left.value);
        assertNull(root.left.right);
        assertEquals(1, root.right.value);
        assertNull(root.right.left);
        assertNull(root.right.right);
    }

    @Test
    void parseLeftSubtree() {
        String input = "(1 (2 (3 (4) null) (5 null (6))))";
        Node root = parse(input);
        assertEquals(1, root.value);
        assertEquals(2, root.left.value);
        assertEquals(3, root.left.left.value);
        assertEquals(4, root.left.left.left.value);
        assertNull(root.left.left.left.left);
        assertNull(root.left.left.left.right);
        assertNull(root.left.left.right);
        assertEquals(5, root.left.right.value);
        assertNull(root.left.right.left);
        assertEquals(6, root.left.right.right.value);
        assertNull(root.left.right.right.left);
        assertNull(root.left.right.right.right);
        assertNull(root.right);
    }

    @Test
    void parseRightSubtree() {
        String input = "(1 (2 (3 null (4)) (5 (6) null)))";
        Node root = parse(input);
        assertEquals(1, root.value);
        assertEquals(2, root.left.value);
        assertEquals(3, root.left.left.value);
        assertNull(root.left.left.left);
        assertEquals(4, root.left.left.right.value);
        assertNull(root.left.left.right.left);
        assertNull(root.left.left.right.right);
        assertEquals(5, root.left.right.value);
        assertEquals(6, root.left.right.left.value);
        assertNull(root.left.right.left.left);
        assertNull(root.left.right.left.right);
        assertNull(root.left.right.right);
        assertNull(root.right);
    }

    @Test
    void illegalEmptyEntry() {
        String input = "";
        assertThrows(IllegalTreeEntry.class, () -> parse(input));
    }

    @Test
    void illegalEmptyEntry2() {
        String input = " (";
        assertThrows(IllegalTreeEntry.class, () -> parse(input));
    }

    @Test
    void illegalTop() {
        String input = "(8";
        assertThrows(IllegalTreeEntry.class, () -> parse(input));
    }

    @Test
    void illegalTop2() {
        String input = "((8";
        assertThrows(IllegalTreeEntry.class, () -> parse(input));
    }

    @Test
    void illegalNull() {
        String input = "(8 nope (4))";
        assertThrows(IllegalTreeEntry.class, () -> parse(input));
    }

    @Test
    void illegalEmptyBracketsAsNull() {
        String input = "(8 () 4)";
        assertThrows(IllegalTreeEntry.class, () -> parse(input));
    }


    @Test
    void illegalEmptyBracketsAsNull2() {
        String input = "(8 4 ())";
        assertThrows(IllegalTreeEntry.class, () -> parse(input));
    }

    @Test
    void illegalBracket() {
        String input = "[8]";
        assertThrows(IllegalTreeEntry.class, () -> parse(input));
    }

    @Test
    void illegalTree() {
        String input = "((4 null (5))";
        assertThrows(IllegalTreeEntry.class, () -> parse(input));
    }

    @Test
    void illegalNodeValue() {
        String input = "(4 (value) null)";
        assertThrows(IllegalTreeEntry.class, () -> parse(input));
    }
}
