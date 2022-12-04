package lab_3;

public class AvlTree implements Tree {
    private Node root;

    public AvlTree() {
        this.root = null;
    }

    public AvlTree(Node root) {
        this.root = root;
        // TODO: Балансировка
    }

    @Override
    public void insert(int value) {
        root = insert(value, root);
    }

    private Node insert(int value, Node node) {
        if (node == null) {
            node = new Node(value);
        } else if (value < node.value) {
            node.left = insert(value, node.left);
        } else if (value > node.value) {
            node.right = insert(value, node.right);
        } else {
            throw new DuplicatedValueException(value);
        }
        return node;
    }

    static Node rotateLeft(Node node) {
        Node child = node.right;
        node.right = child.left;
        child.left = node;
        updateHeight(node);
        updateHeight(child);
        return child;
    }

    static Node rotateRight(Node node) {
        Node child = node.left;
        node.left = child.right;
        child.right = node;
        updateHeight(node);
        updateHeight(child);
        return child;
    }

    static Node rotateBigLeft(Node node) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    static Node rotateBigRight(Node node) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    static int height(Node node) {
        return node != null ? node.height : -1;
    }

    static void updateHeight(Node node) {
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        node.height = Math.max(leftHeight, rightHeight) + 1;
    }

    static int getBalanceFactor(Node node) {
        return height(node.right) - height(node.left);
    }

    @Override
    public Node search(int value) {
        Node node = root;

        while (node != null) {
            if (value == node.value) {
                return node;
            } else if (value < node.value) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return null;
    }

    @Override
    public Node remove(int value) {
        return null;
    }
}
