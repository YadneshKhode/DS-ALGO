//https://www.youtube.com/watch?v=Yt50Jfbd8Po&list=PLJ_vPQ_vraNz90tiB1HNgUWjivW07RcXC&index=14

//https://leetcode.com/problems/balanced-binary-tree/submissions/

public class checkIfTreeIsBalanced {
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        return isMyBalanced(root) == -1 ? false : true;
    }

    public int isMyBalanced(TreeNode root) {
        if (root == null)
            return 0;

        int lh = isMyBalanced(root.left);
        int rh = isMyBalanced(root.right);

        if (lh == -1 || rh == -1)
            return -1;
        if (Math.abs(lh - rh) > 1)
            return -1;

        return 1 + Math.max(lh, rh);
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