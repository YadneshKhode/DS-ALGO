//https://www.youtube.com/watch?v=Yt50Jfbd8Po&list=PLJ_vPQ_vraNz90tiB1HNgUWjivW07RcXC&index=14

//https://leetcode.com/problems/balanced-binary-tree/submissions/

/*
 * 1. We need int because we are just calculating height of the tree
 * 2. Whenever at any node while calculating height if the difference is greater than 1 we immediately start returning -1 since the height can never be negative and this tells us the tree is not balanced
 * 3. Once we return -1 each and every call made previously keeps returning same -1 call and eventually when we reach end we check if we received -1 then return false.
 */

public class checkIfTreeIsBalanced {
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        return isMyBalanced(root) != -1;
        // above is similar to -> return isMyBalanced(root) == -1 ? false : true;
    }

    public int isMyBalanced(TreeNode root) {
        if (root == null)
            return 0;

        int lh = isMyBalanced(root.left);
        int rh = isMyBalanced(root.right);

        if (lh == -1 || rh == -1 || Math.abs(lh - rh) > 1)
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