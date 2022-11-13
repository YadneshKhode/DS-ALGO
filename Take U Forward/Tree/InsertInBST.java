public class InsertInBST {
    /*
     * 1. If root is null then create a new node and return it.
     * 2. create another pointer to modify the tree because in the end we need to
     * return root.
     * 3.if curr node is greater than val the go left else go right, but before
     * going left or right check if it is null if yes then we have to add the new
     * node here, why not move and then check if curr node is null or not because we
     * have to store the new address of inserted node in left or right of the
     * parent.
     * 4. Once we have added new node just break out of the loop
     */
    public TreeNode insertIntoBST(TreeNode root2, int val) {
        if (root2 == null)
            return new TreeNode(val);
        TreeNode root = root2;
        while (true) {
            if (root.val > val) {
                if (root.left != null)
                    root = root.left;
                else {
                    root.left = new TreeNode(val);
                    break;
                }
            } else {
                if (root.right != null)
                    root = root.right;
                else {
                    root.right = new TreeNode(val);
                    break;
                }
            }
        }
        return root2;
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