public class Homework3 {
    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(1);
        tree.add(9);

        System.out.println(tree.contains(3)); // true
        System.out.println(tree.contains(4)); // false
    }

}

class RedBlackTree<T extends Comparable<T>> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        T value;
        Node left;
        Node right;
        boolean color;
        int size;

        public Node(T value, boolean color, int size) {
            this.value = value;
            this.color = color;
            this.size = size;
        }
    }

    public boolean contains(T value) {
        return get(value) != null;
    }

    public void add(T value) {
        root = add(root, value);
        root.color = BLACK;
    }

    private Node add(Node node, T value) {
        if (node == null) {
            return new Node(value, RED, 1);
        }
        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = add(node.left, value);
        } else if (cmp > 0) {
            node.right = add(node.right, value);
        } else {
            node.value = value;
        }
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node get(T value) {
        Node current = root;
        while (current != null) {
            int cmp = value.compareTo(current.value);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current;
            }
        }
        return null;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.color == RED;
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    private Node rotateLeft(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        x.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;
        return x;
    }

    private Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        x.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;
        return x;
    }

    private void flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }
}

