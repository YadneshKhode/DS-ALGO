import java.util.*;;

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