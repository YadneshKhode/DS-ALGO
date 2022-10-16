import java.util.*;

/*
 * The 1st method involves Level Order Traversal and 2nd method involves using calculate the height of binary tree using recursion
 */

public class MaxDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();

        deque.add(root);

        int currSize = 0, level = 0;

        while (!deque.isEmpty()) {
            currSize = deque.size();
            for (int i = 0; i < currSize; i++) {
                TreeNode curr = deque.removeFirst();
                if (curr.left != null)
                    deque.addLast(curr.left);
                if (curr.right != null)
                    deque.addLast(curr.right);
            }
            level++;
        }

        return level;

    }

    public int maxDepth2(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(maxDepth2(root.left), maxDepth2(root.right));

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