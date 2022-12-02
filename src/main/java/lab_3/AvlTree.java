package lab_3;

import java.util.function.Consumer;

public class AvlTree implements Tree {
    private AvlNode root;

    public AvlTree() {
        this.root = null;
    }

    public AvlTree(Node root) {
        this.root = new AvlNode(null, root.value, 1);
        // TODO: Строить дерево по данному
        // breadthFirstSearch(node -> add(node.value));
    }

    static class AvlNode extends Node {
        int height;

        public AvlNode(Node parent, int value, int height) {
            super(parent, value);
            this.height = height;
        }

        @Override
        public String toString() {
            return "AvlNode{" +
                    "value=" + value +
                    ", height=" + height +
                    '}';
        }
    }

    @Override
    public Node add(int value) {
        return null;
    }

    @Override
    public Node find(int value) {
        Node current = root;

        while (current != null) {
            if (value == current.value) {
                return current;
            } else if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    @Override
    public Node remove(int value) {
        return null;
    }

    @Override
    public void depthFirstSearch(Consumer<Node> consumer) {
        DepthFirstSearch.searchPreOrder(root, consumer);
    }

    @Override
    public void breadthFirstSearch(Consumer<Node> consumer) {
        BreadthFirstSearch.search(root, consumer);
    }
}
