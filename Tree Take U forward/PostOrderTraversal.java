
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
        Stack<TreeNode> stack2 = new Stack<>();

        stack1.push(root);

        while (!stack1.isEmpty()) {
            root = stack1.pop();
            stack2.push(root);
            if (root.left != null)
                stack1.push(root.left);
            if (root.right != null)
                stack1.push(root.right);

        }

        while (!stack2.isEmpty())
            postOrder.add(stack2.pop().val);

        return postOrder;

    }
}
