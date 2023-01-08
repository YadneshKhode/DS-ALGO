
// package Tree_Take_U_forward;
import java.util.*;

public class PreOrderTraversal {

    /*
     * 
     * 
     * // https://leetcode.com/problems/binary-tree-preorder-traversal/
     * /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preOrder = new ArrayList<>();
        if (root == null)
            return preOrder;
        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            root = stack.pop();
            preOrder.add(root.val);
            if (root.right != null)
                stack.push(root.right);
            if (root.left != null)
                stack.push(root.left);
        }

        return preOrder;
    }

    public List<Integer> preorderTraversalImitatingRecursion(TreeNode root) {
        List<Integer> arr = new ArrayList<>();
        if (root == null) {
            return arr;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        while (root.left != null) {
            root = root.left;
            deque.addLast(root.left);
        }
        root = deque.removeLast();
        arr.add(root.val);
        root = root.right;
        return arr;
    }
}
