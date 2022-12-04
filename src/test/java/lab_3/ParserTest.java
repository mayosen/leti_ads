package lab_3;

import org.junit.jupiter.api.Test;

import static lab_3.Parser.parse;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void parseRoot() {
        String input = "(8)";
        Node root = parse(input);
        assertEquals(8, root.value);
        assertNull(root.left);
        assertNull(root.right);
    }

    @Test
    void parseLeft() {
        String input = "(8 (900) (null))";
        Node root = parse(input);
        assertEquals(8, root.value);
        assertEquals(900, root.left.value);
        assertNull(root.left.left);
        assertNull(root.left.right);
        assertNull(root.right);
    }

    @Test
    void parseRight() {
        String input = "(8 (null) (1))";
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
        String input = "(8 (9 (51) (null)) (1))";
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
        String input = "(1 (2 (3 (4) (null)) (5 (null) (6))) (null))";
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
        String input = "(1 (2 (3 (null) (4)) (5 (6) (null))) (null))";
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
    void negativeValues() {
        String input = "(0 (-1 (-2) (-100)) (400))";
        Node root = parse(input);
        assertEquals(0, root.value);
        assertEquals(-1, root.left.value);
        assertEquals(-2, root.left.left.value);
        assertEquals(-100, root.left.right.value);
        assertEquals(400, root.right.value);
    }

    @Test
    void emptyEntry() {
        String input = "";
        assertThrows(IllegalTreeException.class, () -> parse(input));
    }

    @Test
    void emptyEntryWithSpace() {
        String input = " (";
        assertThrows(IllegalTreeException.class, () -> parse(input));
    }

    @Test
    void unclosedRoot() {
        String input = "(8";
        assertThrows(IllegalTreeException.class, () -> parse(input));
    }

    @Test
    void unclosedRoot2() {
        String input = "((8";
        assertThrows(IllegalTreeException.class, () -> parse(input));
    }

    @Test
    void illegalNull() {
        String input = "(8 (nope) (4))";
        assertThrows(IllegalTreeException.class, () -> parse(input));
    }

    @Test
    void nullWithoutBrackets() {
        String input = "(8 null (4))";
        assertThrows(IllegalTreeException.class, () -> parse(input));
    }

    @Test
    void emptyBracketsAsNullLeft() {
        String input = "(8 () (4))";
        assertThrows(IllegalTreeException.class, () -> parse(input));
    }

    @Test
    void emptyBracketsAsNullRight() {
        String input = "(8 (4) ())";
        assertThrows(IllegalTreeException.class, () -> parse(input));
    }

    @Test
    void illegalBrackets() {
        String input = "[8]";
        assertThrows(IllegalTreeException.class, () -> parse(input));
    }

    @Test
    void treeWithoutRoot() {
        String input = "((4 (null) (5))";
        assertThrows(IllegalTreeException.class, () -> parse(input));
    }

    @Test
    void illegalNodeValue() {
        String input = "(4 (value) (null))";
        assertThrows(IllegalTreeException.class, () -> parse(input));
    }

    @Test
    void unbalancedEntry() {
        String input = "(1 (2 (3) (4)) (null)";
        assertThrows(IllegalTreeException.class, () -> parse(input));
    }

    @Test
    void nullHasChildren() {
        String input = "(1 (null (2) (3)) (4))";
        assertThrows(IllegalTreeException.class, () -> parse(input));
    }
}
