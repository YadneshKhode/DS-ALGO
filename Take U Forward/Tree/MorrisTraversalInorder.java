
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
                /*
                 * We are not moving root directly because later on we will require reference of
                 * root node so we can point the right most node on left side of the root to the
                 * root node, so after completing left side we can move towards right side
                 */
                TreeNode curr = root.left;
                /*
                 * Trying to find the rightmost node in left subtree, the below for loop has
                 * amortized time complexity of O(N) - approx at 22:00 min in video, inshort
                 * this below loop will in total run for O(N), in each iteration it might run
                 * for the branch length
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
                 * Why rightmost node's of the left subtree's right is root? Why not left?
                 * -> Because when I am at root and go to left part of subtree to find the right
                 * most node of this part of tree I keep going right and right until I encounter
                 * null if I encounter null it means this is the last node that will be visited
                 * (since left,root,right-> inorder traversal rightmost node of any tree is last
                 * visited)
                 * before I have to go back to the root node so I can traverse to right
                 * 
                 * I will always search for right right
                 * right on left subtree
                 * And if I reach root it means I have already visited left
                 * So if I don't do this I won't be able to get back to root just by doing right
                 * right
                 * And hence
                 * And not left
                 * In the base if condition if left == null print node and go right
                 * Whenever we reach leftmost leaf node since it's right is pointing to its
                 * parent root node
                 * 
                 * When we go right we automatically reach its parent but we actually don't know
                 * it is its parent since we just went right But in earlier iteration we made
                 * right most nodes right as the parent node
                 * Now again we check if this node has left it obviously has since we just
                 * traversed but we don't know that we traversed
                 * Now again we check the right most node in left part
                 * While doing so we automatically reach the current node ... Since we had
                 * created pointer in previous iteration So now we understood it is already
                 * visited and we remove this pointer thread created and move right
                 */
                if (curr.right == null) {
                    curr.right = root;
                    root = root.left;
                }
                /*
                 * right Pointing to root again
                 * 
                 * 
                 */
                else {
                    curr.right = null;
                    /*
                     * We have to add inOrder here because at this juncture we have completely
                     * traversed the left part and now are going to move towards the right part
                     */
                    inOrder.add(root.val);
                    root = root.right;
                }
            }
        }
        return inOrder;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();

        if (root == null)
            return preorder;

        while (root != null) {
            if (root.left == null) {
                /*
                 * this below automatically takes care of preorder since left is null we print
                 * current root node and move to right (root,left=null,right)
                 */
                preorder.add(root.val);
                root = root.right;
            } else {
                /*
                 * We are not moving root directly because later on we will require reference of
                 * root node so we can point the right most node on left side of the root to the
                 * root node, so after completing left side we can move towards right side
                 */
                TreeNode curr = root.left;
                /*
                 * Trying to find the rightmost node in left subtree,the below for loop has
                 * amortized time complexity of O(N) - approx at 22:00 min in video
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
                    /*
                     * added preorder here, since we are on root and we are moving to left side
                     * (root,left,right)->preorder
                     */
                    preorder.add(root.val);
                    root = root.left;
                }
                /*
                 * right Pointing to root again
                 */
                else {
                    curr.right = null;
                    /*
                     * removed preorder.add(root.val); from here and added above this is the only
                     * change
                     */
                    root = root.right;
                }
            }
        }
        return preorder;
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