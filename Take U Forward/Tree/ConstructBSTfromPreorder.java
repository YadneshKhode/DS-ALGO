import java.util.*;

//https://www.youtube.com/watch?v=UmJT3j26t1I&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=55
//https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
public class ConstructBSTfromPreorder {
    public TreeNode bstFromPreorderNSquareTC(int[] preorder) {
        /*
         * just iterate array and insert 1 by 1-> TC - O(N^2) because during each
         * insertion we have to traverse whole tree
         */

        TreeNode root = new TreeNode(preorder[0]);

        for (int i = 1; i < preorder.length; i++) {
            TreeNode curr = root;
            insert(curr, preorder[i]);
        }

        return root;
    }

    public void insert(TreeNode root, int key) {
        while (root != null) {
            if (root.val > key) {
                if (root.left == null) {
                    root.left = new TreeNode(key);
                    break;
                }
                root = root.left;
            } else if (root.val < key) {
                if (root.right == null) {
                    root.right = new TreeNode(key);
                    break;
                }
                root = root.right;
            }
        }
    }

    /*
     * Method 2 - sort preorder and get inorder array, now we already know if we
     * have preorder and inorder array how to make a unique tree
     * 
     * SC - O(N) and TC - O(n log n) + O(n);
     */
    public TreeNode bstFromPreorderInorder(int[] preorder) {
        int[] inorder = Arrays.copyOf(preorder, preorder.length);
        Arrays.sort(inorder);
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return bstFromPreorderInorder(preorder, 0, preorder.length, inorder, 0, inorder.length, indexMap);
    }

    public TreeNode bstFromPreorderInorder(int[] preorder, int preorderStart, int preorderEnd, int[] inorder,
            int inorderStart,
            int inorderEnd, Map<Integer, Integer> indexMap) {
        if (preorderStart > preorderEnd || inorderStart > inorderEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preorderStart]);
        int inRoot = indexMap.get(root.val);
        int numsLeft = inRoot - inorderStart;

        root.left = bstFromPreorderInorder(preorder, preorderStart + 1, preorderStart + numsLeft, inorder, inorderStart,
                inRoot - 1,
                indexMap);

        root.right = bstFromPreorderInorder(preorder, preorderStart + numsLeft + 1, preorderEnd, inorder, inRoot + 1,
                inorderEnd,
                indexMap);

        return root;
    }

    /*
     * 
     * 1. When we insert our first node the upper bound is Integer.MAX_VALUE.
     * 2. Now we select 2nd element in the array if we have to place it on the left
     * side of the first node then the max value that 2nd element can have is just
     * less than value of 1st node since this is a BST
     * 3. But if 2nd element is greater than first node then we have to place it on
     * right hand side and now the
     * value can be anything ( < integer.max_value which is current upperbound)
     * 4. we are deep in tree and we want to place new element now to place it on
     * left of curr node it has to be less then curr node so we pass upper bound as
     * curr node and if it is greater than curr node then we pass upper bound same
     * as the upper bound of the parent of curr node since this value cannot be
     * greater than the parent of curr node
     * 5. If both conditions are not met then we have to place this node to the
     * right of curr node's parent's node, again if the condition fails then we have
     * to place it to the right of parent's parent node and so on, until we reach
     * node where upper bound is Integer.max_value and we can place it on its right
     * side
     * 
     * 
     */

    public TreeNode bstFromPreorderMostOptimised(int[] preorder) {
        return bstFromPreorderMostOptimised(preorder, Integer.MAX_VALUE, new int[] { 0 });
    }

    public TreeNode bstFromPreorderMostOptimised(int[] preorder, int upperBound, int[] index) {
        if (index[0] == preorder.length || preorder[index[0]] > upperBound)
            return null;
        TreeNode root = new TreeNode(preorder[index[0]++]);
        root.left = bstFromPreorderMostOptimised(preorder, root.val, index);
        root.right = bstFromPreorderMostOptimised(preorder, upperBound, index);
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