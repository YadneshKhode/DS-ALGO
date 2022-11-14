public class LCAinBST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        /*
         * 1. In normal Binary tree we would have found the path from root to 'p' and
         * root
         * to 'q' and stored in a list and then find intersection in their path by
         * comparing both lists.
         * 
         * 2. Since this is a BST, we have to do it in O(H) H is height of tree.
         * 
         * 3. If p and q both lie on left side of root we go left, else if both lie on
         * right side we go right.
         * 
         * 4. What if 1 lies on left and another lies on right, so current node becomes
         * our answer as the split happened at this node hence it is the lowest common
         * ancestor
         * 
         * 5. what if curr root node is one of p or q, still the condition in point 4
         * will be satisfied, if both p and q does not lie in one side of the tree the
         * current node is the answer
         */

        while (root != null) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
