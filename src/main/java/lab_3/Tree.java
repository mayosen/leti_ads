package lab_3;

import java.util.function.Consumer;

public interface Tree {
    void insert(int value);

    Node search(int value);

    Node remove(int value);

    void depthFirstSearch(Consumer<Node> consumer);

    void breadthFirstSearch(Consumer<Node> consumer);
}
