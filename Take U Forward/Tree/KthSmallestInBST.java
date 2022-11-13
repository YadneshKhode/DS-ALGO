import java.util.*;

public class KthSmallestInBST {
    /*
     * APPROACH 1 - SC & TC - O(N)
     * 1. We know inorder traversal of BST gives ascending order of elements
     * 2. Perform recursive inorder traversal, whenever we visit a root increment
     * global count if count == k then store that root's value in global ans
     * variable and return this ans variable
     */
    int count = 0;
    int ans = 0;

    public int kthSmallestInorderRecursive(TreeNode root, int k) {
        if (root == null)
            return -1;

        kthSmallestInorderRecursive(root.left, k);
        count++;
        if (count == k) {
            ans = root.val;
        }
        kthSmallestInorderRecursive(root.right, k);
        return ans;
    }

    /*
     * APPROACH 2 - SC & TC - O(N)
     * 1. We know inorder traversal of BST gives ascending order of elements
     * 2. Perform iterative inorder traversal, whenever we visit a root increment
     * global count if count == k then store that root's value in global ans
     * variable and return this ans variable
     */
    public int kthSmallestInorderIterative(TreeNode root, int k) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        while (true) {
            if (root != null) {
                deque.addLast(root);
                root = root.left;
            } else {
                if (deque.isEmpty())
                    break;
                root = deque.removeLast();
                count++;
                if (count == k) {
                    ans = root.val;
                    break;
                }
                root = root.right;
            }
        }
        return ans;
    }

    /*
     * * APPROACH 3 - Morris Traversal - SC - O(1) & TC - O(N)
     */
    public int kthSmallestInorderMorris(TreeNode root, int k) {
        while (root != null) {
            if (root.left == null) {
                count++;
                if (count == k)
                    ans = root.val;
                root = root.right;
            } else {
                TreeNode curr = root.left;
                while (curr.right != null && curr.right != root) {
                    curr = curr.right;
                }
                if (curr.right == null) {
                    curr.right = root;
                    root = root.left;
                } else {
                    curr.right = null;
                    count++;
                    if (count == k)
                        ans = root.val;
                    root = root.right;
                }
            }
        }
        return ans;
    }
}

class TreeNode {
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
