package lab3;

import lab1.ArrayList;
import lab1.List;

import java.util.function.Consumer;

public class BreadthFirstSearch {
    /**
     * Обход дерева в ширину.
     *
     * @param root корневой элемент
     * @param consumer функция, которую нужно применить к каждому элементу
     */
    public static void search(Node root, Consumer<Node> consumer) {
        List<Node> queue = new ArrayList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.get(0);
            queue.remove(0);
            consumer.accept(node);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }
}
