
import java.util.*;

/*
 * 1. Using Morris Traversal we traverse the tree in inorder manner in TC - O(N) same as any other algorithm either recursive or iterative, but SC - O(1), others algorith require O(N) space.
 * 
 * 
 * 2. The main reason why we require O(N) space in other algorithms is that once we cover the left side of tree by inorder traversal we are unable to go back to the parent node, we need to go to parent node so we can go to right side of the tree, this is achieved using either recursion (auxillary stack) or using a deque to go to previous node
 * 
 * 3. In Morris Traversal we first check if left node for the tree exists or not, if not then we just have to print current node and move towards right as expected in a inorder traversal (left,root,right)
 * 
 * 4. If left node exists then we have to find the right most node "in this left subtree" and make sure it points to the root of the tree, since in inorder traversal once this right most node is visited we have completely traversed the left part of the tree and now we have to move towards the root node so we can traverse the right part of the tree.
 * 
 * 5. But after whole left subtree is visited and we reach back to parent root how do we know we have traversed the left part and now we need to move to right part thats where this condition comes in "curr.right != root", this means a thread exists from the the right most node in left part of subtree to the root it means we have already traversed this, so now we remove this thread and move towards right
 */

public class MorrisTraversalInorder {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inOrder = new ArrayList<>();

        if (root == null)
            return inOrder;

        while (root != null) {
            if (root.left == null) {
                inOrder.add(root.val);
                root = root.right;
            } else {
                TreeNode curr = root.left;
                /*
                 * Trying to find the rightmost node in left subtree
                 */
                while (curr.right != null && curr.right != root) {
                    curr = curr.right;
                }
                /*
                 * We have reached rightmost node and now we have to make sure its right points
                 * to root and now we can move root to root.left and then again follow all the
                 * steps of finding rightmost etc
                 * 
                 * 
                 * In this moment we already know either our curr points to the rightmost and
                 * the rightmost is pointing to Null OR it is pointing to the root again
                 */

                /*
                 * right Pointing to null
                 */
                if (curr.right == null) {
                    curr.right = root;
                    root = root.left;
                }
                /*
                 * right Pointing to root again
                 */
                else {
                    curr.right = null;
                    inOrder.add(root.val);
                    root = root.right;
                }
            }
        }
        return inOrder;
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