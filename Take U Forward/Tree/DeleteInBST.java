//https://leetcode.com/problems/delete-node-in-a-bst/submissions/

public class DeleteInBST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;
        // case 1: Root.val is key
        TreeNode dummy = root;
        /*
         * Once we remove the key or are not able to find the key we would still require
         * to return the root as using this root we can traverse whole modified tree
         * 
         * what if root is the key so we will have to remove it and join the right half
         * of this root to the max val node on left subtree, if left subtree is null
         * just return right, if right is also null then automatically null will be
         * returned since we are returning right(null)
         */
        if (root.val == key) {
            return treeDeleteAndModifier(root);
        }

        /*
         * since if we are not able to find key our root will point to null eventually
         * and hence we stored the root in dummy variable so we can return original root
         */
        while (root != null) {
            if (root.val > key) {
                /*
                 * I will have to move left to search key, but what if the left is the key?, we
                 * would need the reference of its parent hence we will check if left is the key
                 * or not here itself.
                 * 
                 * checking root's left is not null is necessary because else we will call
                 * null.val
                 */

                if (root.left != null && root.left.val == key) {
                    /*
                     * since it is key remove it using treeDeleteAndModifier, now we will have new
                     * root of this subtree, append it to the parent's left and halt the execution
                     * as we have achieved our goal
                     */

                    root.left = treeDeleteAndModifier(root.left);
                    break;
                } else {
                    /*
                     * simply move to left if it is not key, it doesn't matter if it is null since
                     * if it is we would have completed whole tree's traversal and we did not find
                     * the key.
                     */
                    root = root.left;
                }
            } else {

                if (root.right != null && root.right.val == key) {
                    root.right = treeDeleteAndModifier(root.right);
                    break;
                } else {
                    root = root.right;
                }
            }
        }

        /*
         * dummy has the original root node and inside this tree we have made the
         * modifications
         */

        return dummy;
    }

    public TreeNode treeDeleteAndModifier(TreeNode root) {
        /*
         * if root's left is null and i am here to remove root then we just have to
         * return root.right
         * (research on how to make sure this removed root is garbage collected)
         * 
         * if right was also null this if condition would return null, and that would be
         * valid because the root was removed and now only null is remaining
         */
        if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        } else {
            /*
             * both left and right are not null and hence now we have to find max in left
             * sub tree and points its right to the root.right, since all elements on
             * root.right subtree are sure to be greater than the greatest element on the
             * left subtree
             */
            TreeNode rightChild = root.right;
            /*
             * when we pass root to the maxOnLeftSide function the value of curr root
             * will be lost and hence storing it
             */
            TreeNode maxOnLeft = maxOnLeftSide(root.left);
            /*
             * maxOnLeft's right will always be null since it is the last node in left
             * subtree, as it is the largest it would be last.
             */
            maxOnLeft.right = rightChild; // research on how to make sure root is garbage collected

            return root.left;
            /*
             * since whole right was appended to the max element in the left subtree
             */
        }

    }

    public TreeNode maxOnLeftSide(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }
}
