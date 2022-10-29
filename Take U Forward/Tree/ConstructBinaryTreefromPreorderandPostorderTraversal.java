//https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/

//https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/discuss/161372/Java-Python-Divide-and-Conquer-with-Explanation

import java.util.*;

public class ConstructBinaryTreefromPreorderandPostorderTraversal {
    /*
     * 1. Consider example preorder : 1 2 4 5 3 6; postorder: 4 5 2 6 3 1
     * 
     * We can use same technique used in other problems
     * (ConstructTreeFromPostOrderAndInorder and
     * ConstructTreeFromPreorderAndInorder)
     * 
     * but the issue here is we don't have inorder which explicitly told us
     * "this is left part of tree" and "this is the right part of the tree"
     * 
     * 2. We can easily get the root since in preorder the root is the first element
     * in the array and last element in the postorder array
     * 
     * 3. Now we know preorder means (root,left,right), so the node immediately
     * after root is 100% sure to be on the left side of the tree (you can argue
     * left could be null and this element belongs to right, but we can have 'n'
     * number of variations of tree when we are NOT given inorder array, so I am
     * considering there is always a left) now using this element we can get index
     * of this value in postorder array and now whatever lies on left side of this
     * number including this number in postorder array is the "left" part of the
     * tree, and whatever is after this element just before the last element( which
     * will be of course root) belongs to the right part of the tree
     * 
     * 4. Now what is the proof that this holds true for everything, as per my
     * understanding - (you can do more research) understand 1 thing, assume we are
     * correct and after partition preorder = [2 4 5] and postorder = [4 5 2] now we
     * can see '2' is the new root because 2 is 1st element in preorder and last in
     * postorder and similarly we assume 4 to be part of left tree for this new node
     * '2', and in postorder whatever is on left of '4' including 4 belongs to part
     * of left tree for this new node '2' since in postorder (left,right,root) and
     * whatever is just after this '4' and before root '2' in postorder belongs to
     * right part of the new tree with root node '2'.
     * 
     * 5.
     */
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        /*
         * creating map so we can get index of the last left node in postorder array
         */
        for (int i = 0; i < postorder.length; i++) {
            indexMap.put(postorder[i], i);
        }

        return constructFromPrePost(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1, indexMap);
    }

    public TreeNode constructFromPrePost(int[] preorder, int preorderStart, int preorderEnd, int[] postorder,
            int postorderStart, int postorderEnd, Map<Integer, Integer> indexMap) {

        /*
         * Whenever the start and end pointers cross each other we need to stop
         * processing and return null, since this means whole array has been processed
         * and the size of the array has become 0 it means no left/right sub-tree and
         * hence we return null
         */

        if (preorderStart > preorderEnd || postorderStart > postorderEnd)
            return null;

        TreeNode root = new TreeNode(preorder[preorderStart]);

        if (preorderStart == preorderEnd)
            return root;

        /*
         * We need to get index of last element that belongs to the left tree in
         * postorder array.
         * We know element just after root belongs to the left side of the tree in
         * preorder array
         * hence we do preorderStart+1 and get index at which this value is present in
         * postorder array
         */
        int indexOfLastLeftElement = indexMap.get(preorder[preorderStart + 1]);

        /*
         * the number of elements on left side of "indexOfLastLeftElement" in postorder
         * array belongs to the left side of the tree since postorder =
         * (left,right,root), so "indexOfLastLeftElement - postorderStart + 1" gives the
         * num of nodes
         */

        int numOfNodesOnLeftOfRoot = indexOfLastLeftElement - postorderStart + 1;

        /*
         * Left Part -
         * 
         * 
         * 1. Preorderstart - It will begin just after preorderStart pointer, since in
         * preorder (root,left,right)
         * just after root the left side's first node starts, hence preoderStart for
         * next left subtree will be preorderStart + 1
         * 
         * 2. PreorderEnd - It will end just before the right tree part starts in
         * preorder array since (root,left,right) so just before right the left should
         * end, to calculate this we just need to add "numOfNodesOnLeftOfRoot" to the
         * preorderStart pointer.
         * 
         * 3. Postorderstart - It will start from postorderStart itself because
         * (left,right,root). The left comes first and hence 1st number will be
         * beginning of postorderStart.
         * 
         * 4. PostorderEnd - Here we need to figure out where the nodes belonging to
         * left ends and right
         * starts. whatever is current "postorderStart pointer + numOfNodesOnLeftOfRoot"
         * -> this will directly take us to the first node of the right subtree but we
         * want to go to the last left element in postorder array and hence we need a
         * "-1" here.
         * 
         * for e.g. -> preorder : 1 2 4 5 3 6; postorder: 4 5 2 6 3 1
         * 
         * in post order our postorderEnd should be on 2 ideally since (4,5,2) belongs
         * to the left part of the tree, after doing
         * "current postorderStart pointer + numOfNodesOnLeftOfRoot" we will reach to
         * the "6" but we want to go until "2" and hence our postorderEnd =
         * postorderStart+numOfNodesOnLeftOfRoot-1 (thats why -1 is important here)
         * 
         * 
         * 
         * Right part -
         * 
         * 1. preorderStart -> preorderStart =
         * "preorderStart + 1 + numOfNodesOnLeftOfRoot", because in preorder the right
         * subtree will start from the next node where left subtree ended, to get left
         * subtree end we need "preorderStart + numOfNodesOnLeftOfRoot" but this gives
         * us '5' (preorder : 1 2 4 5 3 6), since preorderStart is '0' and added with
         * numofNodesOnLeftOfRoot ('3' -> (2,4,5)) will give us 0+3 = '3'(this 3 is the
         * index and not value), and we need to be pointing towards '3' value i.e. 4th
         * index
         * 
         * 2. PreorderEnd -> will obviously be current preorderEnd since
         * (root,left,right) and we need last element of right so PreorderEnd is
         * pointing to the last element of the right
         * 
         * 3. Postorderstart -> postorder means (left,right,root) and hence our
         * postorderStart for right subtree will begin just after left ends
         * (Consider example preorder : 1 2 4 5 3 6; postorder: 4 5 2 6 3 1)
         * "postorderStart + numOfNodesOnLeftOfRoot" postorderStart currently is 0 and
         * numOfNodesOnLeftOfRoot is 3 (2-0+1 -> as per formula i used above) gives us
         * '6' that is start of postorderStart for right subtree
         * 4. PostorderEnd -> will be current postorderEnd i.e. (postorder.length-1) - 1
         * since last element is the root in postorder
         */
        root.left = constructFromPrePost(preorder, preorderStart + 1, preorderStart + numOfNodesOnLeftOfRoot,
                postorder, postorderStart, postorderStart + numOfNodesOnLeftOfRoot - 1, indexMap);

        root.right = constructFromPrePost(preorder, preorderStart + 1 + numOfNodesOnLeftOfRoot, preorderEnd, postorder,
                postorderStart + numOfNodesOnLeftOfRoot, postorderEnd - 1, indexMap);

        return root;
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