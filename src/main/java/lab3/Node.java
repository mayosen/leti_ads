package lab3;

import java.util.Objects;

public class Node {
    Node left;
    Node right;
    int value;
    int height;

    public Node(int value) {
        this.left = null;
        this.right = null;
        this.value = value;
        this.height = 0;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", height=" + height +
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
