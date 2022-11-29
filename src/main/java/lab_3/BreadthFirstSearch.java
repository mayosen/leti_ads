package lab_3;

import lab_1.ArrayList;
import lab_1.List;

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
            Node current = queue.get(0);
            queue.remove(0);
            consumer.accept(current);
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }
}
