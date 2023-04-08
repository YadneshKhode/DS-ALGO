//https://leetcode.com/problems/validate-binary-search-tree/
//https://www.youtube.com/watch?v=f-sj7I5oXEI&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=48

public class ValidateBST {

    /*
     * JUST DO INORDER TRAVERSAL AND MAKE SURE CURRENT NUMBER IS GREATER THAN
     * PREVIOUS
     */

    /*
     * 1. We have to use (Long.MIN_VALUE, Long.MAX_VALUE) because of the extreme
     * range of int can also be the value of the node
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long min, long max) {
        /*
         * if the node is null return true.
         * if the value of the node lies between the min and max range then it is a
         * valid node now recursively call the isValidBST function on both left and
         * right
         */
        if (root == null)
            return true;

        if (root.val > min && root.val < max) {
            return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
        } else {
            return false;
        }

    }
}
