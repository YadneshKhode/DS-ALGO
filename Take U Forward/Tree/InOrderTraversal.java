
// package Tree_Take_U_forward;
import java.util.*;

public class InOrderTraversal {

    /*
     * 
     * 
     * // https://leetcode.com/problems/binary-tree-inorder-traversal/
     * /**
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

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inOrder = new ArrayList<>();
        if (root == null)
            return inOrder;
        Stack<TreeNode> stack = new Stack<>();

        /*
         * This condition is not while(!stack.isEmpty()) {keep doing stuff} because when
         * left part of the tree is completely traversed and you are on the root of the
         * whole tree at this point the stack is empty but you have still not traversed
         * the right part of the whole tree, thats why while(true) is necessary so we
         * keep continuing
         */
        while (true) {
            /*
             * We keep going left until we encounter null,now we have traversed left and
             * hence we go to the root by popping from stack, we print root and go right (we
             * dont care if right is null since even if it is we handle it by just popping
             * the top of stack and going to the parent)
             * 
             * 
             */
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                /*
                 * Why (stack.isEmpty()) condition is written here?
                 * Assume you have traversed left part root and the whole right part, at this
                 * point you would be on null and as usual you want to pop top of stack and go
                 * to the parent node so you can traverse further, BUT you have already
                 * traversed the whole tree and now you are at the end and you are trying to pop
                 * an empty stack and hence this condition is needed here.
                 * 
                 * 
                 * When we are on root usually the right is not null and hence we go to right
                 * and resume our steps hence even if stack is empty at that time our root is
                 * not null and hence we push it on stack and move left
                 */
                if (stack.isEmpty())
                    break;
                root = stack.pop();
                inOrder.add(root.val);
                root = root.right;
            }

        }

        return inOrder;

    }
}
