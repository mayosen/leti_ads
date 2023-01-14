package lab3;

public class AvlTree implements Tree {
    private Node root;

    public AvlTree() {
        root = null;
    }

    public AvlTree(Node otherRoot) {
        root = null;
        BreadthFirstSearch.search(otherRoot, node -> insert(node.value));
    }

    public static AvlTree of(int... values) {
        AvlTree tree = new AvlTree();
        for (int value : values) {
            tree.insert(value);
        }
        return tree;
    }

    @Override
    public void insert(int value) {
        root = insert(value, root);
    }

    private static Node insert(int value, Node node) {
        if (node == null) {
            node = new Node(value);
        } else if (value < node.value) {
            node.left = insert(value, node.left);
        } else if (value > node.value) {
            node.right = insert(value, node.right);
        } else {
            throw new DuplicatedValueException(value);
        }
        updateHeight(node);
        return balance(node);
    }

    private static void updateHeight(Node node) {
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        node.height = Math.max(leftHeight, rightHeight) + 1;
    }

    private static int getHeight(Node node) {
        return node != null ? node.height : -1;
    }

    private static Node balance(Node node) {
        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor == -2) {
            if (getBalanceFactor(node.left) <= 0) {
                node = rotateRight(node);
            } else {
                node = rotateBigRight(node);
            }
        } else if (balanceFactor == 2) {
            if (getBalanceFactor(node.right) >= 0) {
                node = rotateLeft(node);
            } else {
                node = rotateBigLeft(node);
            }
        }

        return node;
    }

    private static int getBalanceFactor(Node node) {
        return getHeight(node.right) - getHeight(node.left);
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
    public void delete(int value) {
        root = delete(value, root);
    }

    private static Node delete(int value, Node node) {
        if (node == null) {
            return null;
        } else if (value < node.value) {
            node.left = delete(value, node.left);
        } else if (value > node.value) {
            node.right = delete(value, node.right);
        } else {
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                Node leastMax = findLeastGreater(node.right);
                node.value = leastMax.value;
                node.right = delete(leastMax.value, node.right);
            }
        }

        updateHeight(node);
        return balance(node);
    }

    private static Node findLeastGreater(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    Node getRoot() {
        return root;
    }
}
