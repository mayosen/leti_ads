package lab3;

import lab1.ArrayList;
import lab1.Stack;

import java.util.function.Consumer;

public class DepthFirstSearch {
    /**
     * Рекурсивный обход дерева в глубину в прямом порядке.
     *
     * @param node корневой элемент
     * @param consumer функция, которую нужно применить к каждому элементу
     */
    public static void searchPreOrderRecursive(Node node, Consumer<Node> consumer) {
        if (node != null) {
            consumer.accept(node);
            searchPreOrderRecursive(node.left, consumer);
            searchPreOrderRecursive(node.right, consumer);
        }
    }

    /**
     * Итеративный обход дерева в глубину в прямом порядке.
     *
     * @param root корневой элемент
     * @param consumer функция, которую нужно применить к каждому элементу
     */
    public static void searchPreOrder(Node root, Consumer<Node> consumer) {
        Stack<Node> stack = new ArrayList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            consumer.accept(node);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    /**
     * Итеративный обход дерева в глубину в симметричном порядке.
     *
     * @param root корневой элемент
     * @param consumer функция, которую нужно применить к каждому элементу
     */
    public static void searchInOrder(Node root, Consumer<Node> consumer) {
        Stack<Node> stack = new ArrayList<>();
        Node node = root;

        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                consumer.accept(node);
                node = node.right;
            }
        }
    }

    /**
     * Итеративный обход дерева в глубину в обратном порядке.
     *
     * @param root корневой элемент
     * @param consumer функция, которую нужно применить к каждому элементу
     */
    public static void searchPostOrder(Node root, Consumer<Node> consumer) {
        Stack<Node> stack = new ArrayList<>();
        Node node = root;
        Node lastVisited = null;

        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                Node peek = stack.peek();
                if (peek.right != null && lastVisited != peek.right) {
                    lastVisited = peek.right;
                    node = peek.right;
                } else {
                    consumer.accept(peek);
                    lastVisited = stack.pop();
                }
            }
        }
    }
}
