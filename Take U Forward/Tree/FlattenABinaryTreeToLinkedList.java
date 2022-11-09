
//https://www.youtube.com/watch?v=sWf7k1x9XR4&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=43
//https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
import java.util.*;

public class FlattenABinaryTreeToLinkedList {
    /*
     * Solution 1 - recursive
     * * 1. The order of tree travesal would be right->left->root, because just
     * think about it the right part would always be at the end of the linked list
     * then the elements in the middle would be found on the left part of the tree
     * and the root of the tree would always be at the 0th index of the linked list.
     * 
     * 2. In this algorithm we are first going to the right most part on the right
     * side of the tree(curr node) and making sure its left is null, and whatever
     * was on the left side is on the right side of the curr node
     * 
     * 
     * 3. We are creating a linkedlist from back so for eg our final linked list is
     * 1->2->3->4->null
     * we start creating linkedlist from null then add 4 then 3 and so on until 1
     * 
     * 4. our linkedlistHead is currently pointing to null, now since as explained
     * above the right part of the tree would be in the end of the linkedlist and
     * middle of the linkedlist would consist of the left part of the tree and the
     * root will be the first element of the linkedlist
     * 
     * 5. Now we keep going right until we reach a leaf node, once we reach a node
     * that does not have right we go left once and again check if it has right, if
     * yes then again we keep going right and repeat these steps, why are we doing
     * this? so we reach the deepest last node of the tree the node that is in the
     * last row of the tree and on rightmost side.
     * 
     * 6. Notice, we are going right whenever we get a non-null right value and if
     * we get null right value we are going left and then again we are trying to go
     * right
     * 
     * 7. This ensures we are on the deepest level possible (the max height of tree)
     * and we are on the right most node possible, because this rightmost node will
     * be the last element of the linkedlist
     * 
     * 8. once we reach here, we make the 'right' of curr node to 'null' because
     * head of our linkedlist is currently pointing to the 'null', and we have to
     * make the curr left to be null (1st time obviously it would be null
     * automatically but this will be helpful from 2nd time onwards)
     * 
     * 9. Now our linkedlist head needs to be moved to the current node for e.g. our
     * linkedlist is now 5->NULL
     * 
     * 10. now the function has been completed executed and current instance of the
     * function from call stack has been removed and we go to the previous called
     * function.
     * 
     * 11. Now depending on where we were we will either go on left OR update the
     * linkedlistHead and finish execution of current instance of the function
     * 
     * 12.we always stop our search once both left and right are null and from there
     * we let recursion take care of backtracking, the whole path will perfectly
     * match up with the order of our linkedlist, because we always visited first
     * right then we went left if right was 'NULL' if right was not null we kept
     * going right now while traversing back (we have reached end of recursion and
     * now we are popping and going back up) now we visit the 'left' that was
     * skipped since the right was "not-null" and we chose to go inside right
     * 
     * 13. All this is then again applied on the 'left' node while going back (if it
     * has right keep going right if not then left until both left and right become
     * null we append it to linkedlist and again come back)
     * 
     * 14. Now again, our linkedlistHead is pointing to 5->null, so curr right will
     * point to '5' and left will point to null, new linkedlist becomes 4->5->NULL
     * 
     * 
     * 15. We basically make right side of the tree as straight line then we make
     * left as the straight line and append left tree with right tree
     */

    TreeNode linkedlistHead = null;

    // TC - O(N) and SC - O(N)
    public void flattenUsingRecursion(TreeNode root) {
        if (root == null)
            return;
        /*
         * assume flatten function will flatten right and left
         */
        flattenUsingRecursion(root.right);
        flattenUsingRecursion(root.left);
        root.right = linkedlistHead;
        /*
         * point head of linkedlist to the root.right (since all elements of the left
         * side of the root have been flattened and merged with right side)
         */
        root.left = null; // make whole left as null
        linkedlistHead = root; // store address of root in linkedlistHead
    }

    /*
     * 1. We are doing a normal Preorder traversal, while doing so we are generating
     * a linkedlist.
     * 
     * 2. Add root and run while loop until deque (acting as a stack) is not empty
     * 
     * 3. Add root to deque, pop top of the deque, then add right child first then
     * left child, since it is preorder (root->left->right)
     * 
     * 4. So while traversing we have traversed root node and then we pop from deque
     * and get the left node and then we make right of the root node as the address
     * for the left node just popped from the deque, we make left of root as null
     * and then proceed to the left node and repeat same process until deque is
     * empty(all nodes are processed)
     * 
     * 
     */

    // TC - O(N) and SC - O(N)
    public void flattenUsingIterativeMethod(TreeNode root) {
        if (root == null)
            return;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addFirst(root);

        while (!deque.isEmpty()) {
            TreeNode curr = deque.removeFirst();
            if (curr.right != null)
                deque.addFirst(curr.right);
            if (curr.left != null)
                deque.addFirst(curr.left);

            if (!deque.isEmpty()) {
                curr.right = deque.peekFirst();
            }
            curr.left = null;

        }
    }

    // TC - O(N) and SC - O(1) (Space complexity is O(1) where as for others it was
    // O(N))

    // Below code is written by me and it is not same as striver's code

    /*
     * 1. Remember we can use morris traversal to traverse tree in perorder as well
     * as inorder manner in O(1) space complexity.
     * 
     * 2. Just 1 change is needed instead of making the right most node's (in left
     * sub-tree) right (leftSubTreeRightMostNode.right =
     * root.right), basically instead of pointing to the root we point to root's
     * right.
     * 
     * Why? because we have to save space complexity so we cant use stack or any
     * other deque using which we can traverse to the parent node from the current
     * node, earlier we would just push parent to stack and go to its child and
     * whenever we wanted to come up we would just pop and get parent of curr node.
     * 
     * Now what we are doing to prevent this situation is pointing the rightmost
     * node in left-subtree to the root.right, 2 questions arise why only
     * left-subtree and why root.right, only left-subtree because obviously we are
     * doing (pre-order traversal root-> left-> right), if left is not present we
     * simply moves towards right in other words we can say (imagine structure of
     * linked-list it is root -> all left nodes -> all right nodes -> null) there
     * are no 'left-nodes' to add between root and root.right
     * 
     * why root.right,
     * because in the final linkedlist root is connected to the 1st node of
     * left sub tree and then last node of the left sub tree is connected to
     * the first node of the right sub tree, and last node of right sub tree points
     * to null.
     * 
     * the rightmost node in the left sub tree is THE last node of the left side
     * linkedlist that needs to be connected to the first right side node
     */
    public void flattenUsingMorrisMethod(TreeNode root) {
        if (root == null)
            return;

        // * watch morris traversal for explanation
        while (root != null) {
            /*
             * If current root left is null just go to the right since in preorder traversal
             * (root-> left-> right) if left is not there we just proceed to right, if left
             * is not present then we dont have anything to add between the root and
             * root.right
             */
            if (root.left == null) {
                root = root.right;
            } else {
                TreeNode rightMostNodeFindingPointer = root.left;
                /*
                 * we have to keep going until both left and right part of the node is null,
                 * since this node will be truly last node in the left sub-tree
                 * 
                 * How are we sure it is the last node in left sub tree?
                 * 
                 * 1. Because we want to find the last node that will be traversed in a preorder
                 * traversal, so we keep going right until it is possible to go right, once we
                 * reach a position where the right of rightMostNodeFindingPointer is null this
                 * is the potential candidate to become the last element of preorder traversal
                 * in the left subtree, but if it has a left node then we need to go to left
                 * once and again try to find the last node(keep going right until null and if
                 * left is there go left and keep doing same until both right and left are null)
                 * 
                 * 2. now make the right of this node point to the right of the parent (root)
                 * node
                 * 
                 * 3. Now we have stored the address of root.right successfully and hence we can
                 * alter the root.right to point to root.left so we can create our linkedlist
                 * 
                 * 4. and then we have to do root = root.right (since this 'right' is the actual
                 * left but we altered it above) and continue with the preorder traversal for
                 * the root with modified 'left' and 'right'
                 */
                while (rightMostNodeFindingPointer.right != null || rightMostNodeFindingPointer.left != null) {
                    while (rightMostNodeFindingPointer.right != null) {
                        rightMostNodeFindingPointer = rightMostNodeFindingPointer.right;
                    }
                    if (rightMostNodeFindingPointer.left != null)
                        rightMostNodeFindingPointer = rightMostNodeFindingPointer.left;
                }

                rightMostNodeFindingPointer.right = root.right;

                root.right = root.left;
                root.left = null;
                root = root.right;
            }
        }
    }
}
