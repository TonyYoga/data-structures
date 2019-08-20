package trees;

import java.util.function.Consumer;

public class BSTree {
    private Node root;

    public void add(int value) {
        if (root == null) {
            root = new Node(value);
            return;
        }
        Node curr = root;
        while (true) {
            if (value == curr.value) {
                break;
            }
            if (value > curr.value) {
                if (curr.right == null) {
                    curr.right = new Node(value);
                    break;
                }
                curr = curr.right;
            } else {
                if (curr.left == null) {
                    curr.left = new Node(value);
                    break;
                }
                curr = curr.left;
            }
        }
    }

    public void traverse(Consumer<Integer> consumer) {

    }

    private void traverseRecursive(Node node, Consumer<Integer> consumer) {
        if (node != null) {
            traverseRecursive(node.left, consumer);
            traverseRecursive(node.right, consumer);
            consumer.accept(node.value);
        }
    }
    private class Node{
        private int value;
        private Node left;
        private Node right;

        private Node(int value) {
            this.value = value;
        }
    }
}
