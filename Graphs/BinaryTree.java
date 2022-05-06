package Graphs;

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    TreeNode(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

public class BinaryTree {
    TreeNode root;

    BinaryTree() {
        root = null;
    }

    BinaryTree(int key) {
        root = new TreeNode(key);
    }

    public void add(int value) {
        root = addRecursive(root, value);
    }

    public TreeNode addRecursive(TreeNode node, int value) {
        if (node == null) {
            return new TreeNode(value);
        }
        if (value < node.data) {
            addRecursive(node.left, value);
        }
        return new TreeNode(value); // delete this statement
    }
}