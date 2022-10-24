import java.util.*;

public class BinarySearchTree {

    // ---------------------Definition of tree-----------------------
    class Node {

        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    Node root;

    BinarySearchTree() {
        root = null;
    }
    // -----------------------Insertion----------------------------

    void insert(int data) {
        root = insertRec(root, data);
    }

    Node insertRec(Node root, int data) {
        if (root == null) {
            root = new Node(data);
        } else if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }
        return root;
    }

    // -------------------Traversal-----------------------

    void inorder() {
        inorderRec(root);
    }

    void preorder() {
        preorderRec(root);
    }

    void postorder() {
        postorderRec(root);
    }

    void levelorder() {
        levelorderRec(root);
    }

    // A utility function to
    // do inorder traversal of BST
    void inorderRec(Node root) {
        if (root == null)
            return;
        inorderRec(root.left);
        System.out.println(root.data);
        inorderRec(root.right);
    }

    void preorderRec(Node root) {
        if (root == null)
            return;
        System.out.println(root.data);
        inorderRec(root.left);
        inorderRec(root.right);

    }

    void postorderRec(Node root) {
        if (root == null)
            return;
        inorderRec(root.left);
        inorderRec(root.right);
        System.out.println(root.data);

    }

    void levelorderRec(Node root) {

        if (root == null) {
            System.out.println("Error: The Tree is empty");
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() != 0) {
            root = queue.poll();
            System.out.println(root.data);
            if (root.left != null)
                queue.add(root.left);
            if (root.right != null)
                queue.add(root.right);
        }

    }

    // ---------------------Iterative Traversal------------------------

    void inorderIterative(Node root) {
        if (root == null)
            return;

        // Stack<Node> stack = new Stack<>();

    }

    void preorderIterative(Node root) {
        if (root == null)
            return;

        Stack<Node> stack = new Stack<>();

        stack.push(root);

        System.out.print(stack.peek().data);

        while (stack.size() != 0) {
            while (stack.peek().left != null) {
                stack.push(stack.peek().left);
                System.out.println(stack.peek().data);
            }
            while (stack.peek().right != null) {
                stack.push(stack.peek().right);
                System.out.println(stack.peek().data);
            }
        }
    }

    void postorderIterative(Node root) {
        if (root == null)
            return;
    }

    // ---------------------Search------------------------

    boolean search(int data) {
        return searchRec(root, data);
    }

    boolean searchRec(Node root, int data) {

        if (root == null)
            return false;
        else if (root.data == data)
            return true;

        else if (data < root.data)
            return searchRec(root.left, data);
        else
            return searchRec(root.right, data);
    }

    // -------------------Min-Max--------------------

    int minOfTree() {
        return minOfTreeRec(root);
    }

    int maxOfTree() {
        return maxOfTreeRec(root);
    }

    int minOfTreeRec(Node root) {

        if (root == null)
            return -1;

        while (root.left != null) {
            root = root.left;
        }
        return root.data;
    }

    int maxOfTreeRec(Node root) {

        if (root == null)
            return -1;

        while (root.right != null) {
            root = root.right;
        }
        return root.data;
    }

    // -------------------Find Height--------------------
    int findHeight() {
        return findHeightRec(root);
    }

    int findHeightRec(Node root) {
        if (root == null)
            return -1;
        return Math.max(findHeightRec(root.left), findHeightRec(root.right)) + 1;
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);

        // level order traversal
        System.out.println("Level Order Traversal");
        tree.levelorder();
        // print preorder traversal of the BST
        System.out.println("Pre-Order Traversal");
        tree.preorder();
        // print inorder traversal of the BST
        System.out.println("In-Order Traversal");
        tree.inorder();
        System.out.println("Post-Order Traversal");
        // print postorder traversal of the BST
        tree.postorder();
        // print min
        System.out.println("Minimum in tree = " + tree.minOfTree());
        // print max
        System.out.println("Maximum in tree = " + tree.maxOfTree());
        // search
        if (tree.search(3)) {
            System.out.println("Searched element found");
        } else {
            System.out.println("Searched element not found");
        }
    }
}
