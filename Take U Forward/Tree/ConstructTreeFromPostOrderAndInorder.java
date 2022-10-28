import java.util.*;

//https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
//https://www.youtube.com/watch?v=LgLRTaEMRVc&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk

public class ConstructTreeFromPostOrderAndInorder {
    /*
     * Algorithm:
     * same as constructTreeFromPreorderAndInorder
     */

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        /*
         * creating hashmap<value,index> of inorder array so that we can get root's
         * index in O(1) time
         */
        Map<Integer, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        // using method overloading
        return buildTree(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, indexMap);
    }

    public TreeNode buildTree(int[] postorder, int postorderStart, int postorderEnd, int[] inorder, int inorderStart,
            int inorderEnd, Map<Integer, Integer> indexMap) {

        /*
         * Whenever the start and end pointers cross each other we need to stop
         * processing and return null, since this means whole array has been processed
         * and the size of the array has become 0 it means no left/right sub-tree and
         * hence we return null
         */

        if (postorderStart > postorderEnd || inorderStart > inorderEnd)
            return null;

        /*
         * Creating new tree here with initial root node as last element of postorder
         * array because we know postorder means left,right,root -> so last element will
         * always be root in postorder array
         */
        TreeNode root = new TreeNode(postorder[postorderEnd]);

        /*
         * 1. Now we know the current parent node of the tree, so we try to find this
         * root inside inorder array.
         * 2. Using hashmap we get index of the current root in inorder array in O(1)
         * time
         * 3. whatever is on the left of the root comes in left side of the tree and
         * same for the right side
         * 4. Now we can use recursion on the individual parts of the array one is the
         * left side of the root and another is right side of the root
         * 5. We need to calculate new preorder and inorder array starting and ending
         * point once for the left part and once for the right part
         */

        int rootIndexInInorder = indexMap.get(postorder[postorderEnd]);

        /*
         * numsOnLeftOfRoot is the number of elements on left side of the root in
         * inorder array, using this count will be helpful in partitioning postorder
         * array into left,right,root
         * 
         * numsOnLeftOfRoot = currentRoot index in inorder array subtracted whatever is
         * the inorderStart
         */
        int numsOnLeftOfRoot = rootIndexInInorder - inorderStart;

        /*
         * 1. postorderStartForLeft = postorderStart - for left side of the tree will
         * remain as it is because in
         * postorder left side comes first (left,right,root), so the starting point of
         * new tree will also be the same everytime.
         * 
         * 2. postorderEndForLeft = postorderStart + numsOnLeftOfRoot - 1
         * 
         * This can also be understood by -> inorderNewEnd-inorderOldStart OR ->
         * rootIndexInInorder-inorderStart-1 // notice: numsOnLeftOfRoot =
         * rootIndexInInorder-inorderStart
         * 
         * 3. the value of above result gives us the actual "index" value where the new
         * preorder array Ends and hence the - 1 (without -1 it just tells us the
         * elements between new inorder array of left part) without -1 (for e.g. in ->
         * inorderNewEnd-inorderOldStart) it directly tells us the index
         * 
         * 4. Now notice we have added "postorderStart" in the parameter of postorderEnd
         * below ("postorderStart" + numsOnLeftOfRoot - 1), we understood -1 is to get
         * index, numsOnLeftOfRoot gives count of nodes that would be in this newly
         * formed postorder array, we need this "postorderStart" Because
         * "STARTING FROM postorderStart index" we need "numsOnLeftOfRoot" number of
         * elements, this postorderStart keeps on changing we will see in right side of
         * root
         * 
         * 5. postorderStartForRight = postorderStart + numsOnLeftOfRoot -
         * Similarly the postorderStart for right sub-tree begins after all left nodes
         * are done (left,right,root)
         * so postorderStart(0) + numsOnLeftOfRoot = the starting index of
         * postorderStartForRight (notice we are not doing -1 because 0 + no. of nodes
         * will automatically give next index)
         * 
         * 6. postorderEndForRight = postorderEnd - 1
         * 
         * this one is simple as the last element is root the right will go until its
         * previous index since (left,right,root)
         * 
         * 
         * Now for Inorder
         * 
         * 1. inorderStartForLeft = inorderStart -> as inorder means (left,root,right)
         * the beginning of left tree will be from inorderStart itself
         * 
         * 2. inorderEndForLeft = rootIndexInInorder - 1 -> number of elements between
         * root and beginning of inorder array, -1 is to get index number
         * 
         * 3. inorderStartForRight = rootIndexInInorder + 1 // since just after the root
         * index we will start the right partition (left,root,right)
         * 
         * 4. inorderEndForRight = inorderEnd // since (left,root,right) the last
         * element will be the last of right section
         * 
         * 
         */

        root.left = buildTree(postorder, postorderStart, postorderStart + numsOnLeftOfRoot - 1, inorder, inorderStart,
                rootIndexInInorder - 1, indexMap);

        // rootIndexInInorder-inorderStart === postorderend+1
        root.right = buildTree(postorder, postorderStart + numsOnLeftOfRoot, postorderEnd - 1, inorder,
                rootIndexInInorder + 1, inorderEnd, indexMap);

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