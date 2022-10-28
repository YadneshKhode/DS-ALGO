import java.util.*;

public class ConstructTreeFromPreorderAndInorder {

    // https://www.youtube.com/watch?v=aZNaLrVebKQ&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=38
    // https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
    /*
     * 
     * Also refer -> ConstructTreeFromPostOrderAndInorder.java
     * 
     * 
     * Algorithm:
     * 1. Using Preorder array get the root and then search for root in Inorder
     * array, whatever is on the left is the left side of the tree and whatever is
     * on right of the root inside the inorder array is the right side of the tree
     * 
     * 2.Now you get 2 smaller arrays (lets name them inorderLeftPartSplit1,
     * inorderRightPartSplit1) (one is left of tree - the part on left side
     * of root in inorder array and the right side is 2nd array).
     * 
     * 3. Now on each array we have to apply the same logic mentioned in point - 1,
     * but the issue is now we have inorder array (since subset of an inorder array
     * will still be an inorder array), but we don't have preorder array
     * 
     * 4. Lets first work on inorderLeftPartSplit1, We calculate length of
     * inorderLeftPartSplit1 array and the number of elements is equal to the number
     * of elements in preorder array after root.
     * 
     * for e.g.
     * Inorder - [40,20,50,10,60,30] -> left,root,right
     * Preorder -[10,20,40,50,30,60] -> root,left,right
     * 
     * 1. we know 10 is the root for sure in preorder, we find 10 in inorder array
     * now whatever is on left of 10 in inorder is the left part of resultant tree
     * and same for right part.
     * 
     * 2. now new array becomes - considering only left part
     * Inorder - [40,20,50]
     * But, we dont know what is preorder for this, so we calculate length of
     * inorder and then get as many elements after the root in preorder
     * 
     * since length is 3 now we choose 3 elements after 10 in preorder array
     * 
     * so now,
     * Inorder - [40,20,50]
     * Preorder - [20,40,50]
     * 
     * Now just apply the same algorithm on this and keep doing it, if there is
     * nothing on left or right in inorder array then append null and make curr node
     * as left child of the parent, now when both left and right are done the root
     * gets returned and it is attached to the left of its parent
     * 
     * 
     * same needs to be done for right side of the tree Inorder - [60,30] and
     * preorder - [30,60]
     * 
     */

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, indexMap);
    }

    public TreeNode buildTree(int[] preorder, int preorderStart, int preorderEnd, int[] inorder, int inorderStart,
            int inorderEnd, Map<Integer, Integer> indexMap) {
        if (preorderStart > preorderEnd || inorderStart > inorderEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preorderStart]);
        int inRoot = indexMap.get(root.val);
        int numsLeft = inRoot - inorderStart;

        root.left = buildTree(preorder, preorderStart + 1, preorderStart + numsLeft, inorder, inorderStart, inRoot - 1,
                indexMap);

        root.right = buildTree(preorder, preorderStart + numsLeft + 1, preorderEnd, inorder, inRoot + 1, inorderEnd,
                indexMap);

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