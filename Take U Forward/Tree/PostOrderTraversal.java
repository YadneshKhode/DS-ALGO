
// package Tree_Take_U_forward;
import java.util.*;

public class PostOrderTraversal {

    /*
     * Intuition - postorder traversal means left right root
     * preorder traversal means root left right
     * so the only difference is postorder is reverse of preorder AND left and right
     * are interchanged/swapped
     * 
     * in preorder traversal in the stack when we pop root from stack we push right
     * child of the root first and then we push left child of the root into the
     * stack so when we are popping from stack we would get root -> left -> right
     * 
     * 
     * so in postorder we have to do exact same thing just 1 change instead of
     * pushing right first we have to push left child of root first and then right
     * child of the root so while popping we get right -> left
     * 
     * 
     * similarly to keep track of root we need another stack to push the currently
     * popped root so in the end when we reverse it we will get our answer
     * 
     */

    // https://leetcode.com/problems/binary-tree-postorder-traversal/
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    public class TreeNode {
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

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postOrder = new ArrayList<>();
        if (root == null)
            return postOrder;
        Stack<TreeNode> stack1 = new Stack<>();

        stack1.push(root);
        /*
         * 1. In the postorder list we are adding root first then all the elements on
         * the right of the root and then all the elements on the left side of the root
         * and then we are reversing the postorder list to get proper root-> left ->
         * right that represents the postorder
         * 
         * 2. This seems to be vaguely similar to preorder, since we are doing ROOT ->
         * RIGHT -> LEFT, just the difference is right and left are swapped - check the
         * preorder logic we add right first to the stack and then we add left
         * 
         * 3. Here we are adding left then we add right and then reverse the whole thing
         * thats the only difference
         * 
         * -> Step 1 -> ROOT RIGHT LEFT (after swapping left and right in preorder)
         * -> Step 2 -> Reverse
         * -> Step 3 -> LEFT RIGHT ROOT
         */
        while (!stack1.isEmpty()) {
            root = stack1.pop();
            postOrder.add(root.val);
            if (root.left != null)
                stack1.push(root.left);
            if (root.right != null)
                stack1.push(root.right);

        }
        Collections.reverse(postOrder);
        return postOrder;

    }
}
