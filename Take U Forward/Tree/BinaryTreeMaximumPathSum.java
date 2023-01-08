//https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
//https://youtu.be/WszrfSwMz58

/*
 * 1. Just use same logic of finding height of tree.
 * 2. At each node before returning check left path sum and right path sum and update global "sum" with max value.
 * 3. then we know from that particular node we can either choose left path or right path sum to send above node
 * 4. We choose whichever is max of left or right sum and add current node's value and return the sum to upper node
 * 5. if the net summation (current node's value and max(leftSum,rightSum)) is less than 0 that is negative we can just remove this node from the path as it is not contributing anything, it is degrading our max sum so just send 0 instead meaning we ignored the node and subsequent children of this node
 */
public class BinaryTreeMaximumPathSum {

    private int sum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxPathSumRecur(root);
        return sum;
    }

    public int maxPathSumRecur(TreeNode root) {
        if (root == null)
            return 0;
        int lh = maxPathSumRecur(root.left);
        int rh = maxPathSumRecur(root.right);
        sum = Math.max(sum, root.val + lh + rh);
        int returnVal = root.val + Math.max(lh, rh);
        return returnVal > 0 ? returnVal : 0;
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
