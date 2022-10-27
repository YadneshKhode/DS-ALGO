//https://leetcode.com/problems/count-complete-tree-nodes/
//https://www.youtube.com/watch?v=u-yWemKGWO0&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=33

/*
 * 1. The most obvious solution is to do any traversal and count the number of nodes.(TC - O(N))
 * SC - (Log n) -> log n because this is a complete binary tree and it will never be a skew tree so during traversal at any point the max number of nodes in a stack will be height of the tree that is log n
 * 
 * 2. Since this is a complete binary tree the Time complexity can be optimised.
 * At each node we calculate the height of the left tree and right tree (in log n time, usually it would be O(n)    time but since this is a complete binary tree it would take log n time to get height of the tree - will explain how later) if it is same then we can just use formula -> 2^(height-1) = number of nodes in the complete binary tree, and return this number of nodes.
 * 
 * 3. If left and right tree height is not same then we add 1 (for the current node) and again recursively call the function to calculate the height of the tree.
 */
public class CountNodesInCompleteBinaryTree {
    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;

        int leftHeight = calculateLeftHeight(root);
        int rightHeight = calculateRightHeight(root);

        if (leftHeight == rightHeight) {
            return (2 << leftHeight) - 1;
        }

        return countNodes(root.left) + countNodes(root.right) + 1;

    }

    /*
     * Keep going left as right will always be filled and hence we
     * can easily calculate height by just counting left nodes
     */
    private int calculateLeftHeight(TreeNode root) {
        int count = 0;
        while (root.left != null) {
            count++;
            root = root.left;
        }
        return count;
    }

    /*
     * Keep going right as left will always be filled except last level where left
     * could be filled and right might be empty and in this case there will be
     * mismatch of leftHeight and right height and we would know this binary tree is
     * not a "full binary tree" and in such scenario we cannot use formula
     * 2^(height-1) and so we just add 1 (to account for current node) and
     * recursively call same function (countNodes) with root.left and root.right
     * hoping their height are same if they are same this time then we can simply
     * use formula and return number of nodes below this node and return the count
     */
    private int calculateRightHeight(TreeNode root) {
        int count = 0;
        while (root.right != null) {
            count++;
            root = root.right;
        }
        return count;
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