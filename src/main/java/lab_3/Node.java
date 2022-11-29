package lab_3;

import java.util.Objects;

public class Node {
    Node parent;
    Node left;
    Node right;
    int value;

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
