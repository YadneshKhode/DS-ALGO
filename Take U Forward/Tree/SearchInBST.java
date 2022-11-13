public class SearchInBST {
    public TreeNode searchBST(TreeNode root, int val) {
        /*
         * if val is less than root go left else right, if we find value or root becomes
         * null break and return;
         */
        while (root != null) {
            if (root.val == val)
                break;
            if (root.val > val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return root;
    }
}
