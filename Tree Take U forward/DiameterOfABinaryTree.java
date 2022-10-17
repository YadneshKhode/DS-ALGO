//https://www.youtube.com/watch?v=Rezetez59Nk&list=PLJ_vPQ_vraNz90tiB1HNgUWjivW07RcXC&index=15

//https://leetcode.com/problems/diameter-of-binary-tree/

public class DiameterOfABinaryTree {
    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        diameterOfBinaryTree2(root);
        return max;
    }

    public int diameterOfBinaryTree2(TreeNode root) {
        if (root == null)
            return 0;
        int lh = diameterOfBinaryTree2(root.left);
        int rh = diameterOfBinaryTree2(root.right);

        if ((lh + rh) > max)
            max = lh + rh;

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