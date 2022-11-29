package lab_3;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static lab_3.BinaryTree.Node;

public class Parser {
    private static final int OPENING = '(';
    private static final int CLOSING = ')';
    private static final int SPACE = ' ';

    private final Reader reader;
    private Character lastChar;
    private int balance;

    Parser(Reader reader) {
        this.reader = reader;
        this.lastChar = null;
        this.balance = 0;
    }

    /**
     * Парсинг скобочной записи дерева из строки.
     *
     * @param input запись дерева
     * @return корневой элемент дерева
     */
    public static Node parse(String input) {
        return parse(new StringReader(input));
    }

    /**
     * Парсинг скобочной записи дерева из потока символов.
     *
     * @param reader источник записи дерева
     * @return корневой элемент дерева
     */
    public static Node parse(Reader reader) {
        try {
            Parser parser = new Parser(reader);
            Node root = parser.parseNode(null);
            if (parser.balance != 0) {
                throw new IllegalTreeEntry("Скобки не сбалансированы");
            }
            return root;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private Node parseNode(Node parent) throws IOException {
        char current = readChar(true);

        if (current == OPENING) {
            balance++;
            Integer value = parseValue();
            current = readChar(true);

            if (value == null) {
                if (current != CLOSING) {
                    throw new IllegalTreeEntry("null не может иметь детей");
                }
                balance--;
                return null;
            }

            Node node = new Node(parent, value);

            if (current == CLOSING) {
                balance--;
                return node;
            } else if (current == OPENING) {
                lastChar = OPENING;
                node.left = parseNode(node);
                node.right = parseNode(node);
                current = readChar(true);
                if (current != CLOSING) {
                    throw new IllegalTreeEntry("Ожидался символ ')'");
                }
                balance--;
                return node;
            } else {
                throw new IllegalTreeEntry("Ожидался символ '(' или ')'");
            }
        } else {
            throw new IllegalTreeEntry("Узел должен начинаться с '('");
        }
    }

    private Integer parseValue() throws IOException {
        char current = readChar(false);

        if (current == 'n') {
            boolean flag = readChar(false) == 'u';
            flag &= readChar(false) == 'l';
            flag &= readChar(false) == 'l';

            if (!flag) {
                throw new IllegalTreeEntry("Неверная запись null узла");
            }
            return null;

        } else if (Character.isDigit(current)) {
            int value = Character.getNumericValue(current);
            current = readChar(false);

            while (Character.isDigit(current)) {
                value = 10 * value + Character.getNumericValue(current);
                current = readChar(false);
            }

            lastChar = current;
            return value;

        } else {
            throw new IllegalTreeEntry("Некорректное значение узла");
        }
    }

    private char readChar(boolean skipSpaces) throws IOException {
        Character value = lastChar;
        lastChar = null;

        while (value == null || (skipSpaces && value == SPACE)) {
            value = (char) reader.read();
        }

        return value;
    }
}
