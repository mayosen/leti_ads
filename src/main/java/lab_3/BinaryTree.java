package lab_3;

import java.util.Objects;
import java.util.function.Consumer;

public class BinaryTree {
    public static class Node {
        public Node parent;
        public Node left;
        public Node right;
        public int value;

        public Node(Node parent, int value) {
            this.parent = parent;
            this.left = null;
            this.right = null;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return value == node.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    /**
     * Рекурсивно создает двоичное дерево из limit узлов.
     *
     * @param parent корневой элемент
     * @param limit  максимальное значение узла
     */
    public static void buildTree(Node parent, int limit) {
        int leftValue = parent.value * 2;
        if (leftValue <= limit) {
            parent.left = new Node(parent, leftValue);
            buildTree(parent.left, limit);

            int rightValue = leftValue + 1;
            if (rightValue <= limit) {
                parent.right = new Node(parent, rightValue);
                buildTree(parent.right, limit);
            }
        }
    }

    /**
     * Рекурсивный обход дерева в глубину.
     *
     * @param node корневой элемент
     * @param consumer функция, которую нужно применить к каждому узлу
     */
    public static void depthFirstSearch(Node node, Consumer<Node> consumer) {
        if (node != null) {
            consumer.accept(node);
            depthFirstSearch(node.left, consumer);
            depthFirstSearch(node.right, consumer);
        }
    }
}
