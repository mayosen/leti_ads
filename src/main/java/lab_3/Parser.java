package lab_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static lab_3.BinaryTree.Node;

public class Parser {
    private static final int OPENING = '(';
    private static final int CLOSING = ')';
    private static final int SPACE = ' ';

    private final char[] chars;
    private final Node root;
    private int position;

    // TODO: Перейти на Reader? Пропускать пробелы, ловить конец строки

    Parser(String input) {
        int[] ints = input.chars().filter(c -> c != SPACE).toArray();
        if (ints.length == 0) {
            throw new IllegalTreeEntry();
        }

        chars = new char[ints.length];
        for (int i = 0; i < ints.length; i++) {
            chars[i] = (char) ints[i];
        }

        if (chars[0] == OPENING) {
            position = 1;
            root = new Node(null, parseValue());
        } else {
            throw new IllegalTreeEntry();
        }
    }

    public static void main(String[] args) {
        Node root = parseFile("input.txt");
        BinaryTree.depthFirstSearch(root, System.out::println);
    }

    /**
     * Парсинг скобочной записи дерева из файла со скобочной записью.
     *
     * @param file путь до файла
     * @return корневой элемент созданного дерева
     */
    public static Node parseFile(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return parse(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * Парсинг скобочной записи дерева из строки.
     *
     * @param input строка, описывающее дерево
     * @return корневой элемент созданного дерева
     */
    public static Node parse(String input) {
        Parser parser = new Parser(input);
        if (parser.position == parser.chars.length) {
            throw new IllegalTreeEntry();
        }
        parser.parse(parser.root, true);
        if (parser.position < parser.chars.length) {
            parser.parse(parser.root, false);
        }
        return parser.root;
    }

    /**
     * Рекурсивное чтение узла дерева.
     *
     * @param parent родитель текущего узла (нужен для создания двусторонней связи)
     * @param isLeftChild является ли текущий узел левым потомком (иначе правый)
     */
    private void parse(Node parent, boolean isLeftChild) {
        char current = chars[position];

        if (current == 'n') {
            position++;
            parseNull();
            return;
        } else if (current == CLOSING) {
            position++;
            return;
        } else if (current == OPENING) {
            position++;
            current = chars[position];
            if (!Character.isDigit(current)) {
                throw new IllegalTreeEntry();
            }
        }

        Node node = new Node(parent, parseValue());

        if (isLeftChild) {
            parent.left = node;
        } else {
            parent.right = node;
        }

        if (chars[position] != CLOSING) {
            parse(node, true);
            parse(node, false);
        }
        position++;
    }

    /**
     * Проверка корректности написания нулевого потомка.
     */
    private void parseNull() {
        boolean flag = chars[position++] == 'u';
        flag &= chars[position++] == 'l';
        flag &= chars[position++] == 'l';
        if (!flag) {
            throw new IllegalTreeEntry();
        }
    }

    /**
     * Чтение значения узла из записи дерева.
     *
     * @return прочитанное значение
     */
    private int parseValue() {
        StringBuilder builder = new StringBuilder();
        for (; position < chars.length; position++) {
            char current = chars[position];
            if (Character.isDigit(current)) {
                builder.append(current);
            } else {
                break;
            }
        }
        if (builder.isEmpty()) {
            throw new IllegalTreeEntry();
        }
        return Integer.parseInt(builder.toString());
    }
}
