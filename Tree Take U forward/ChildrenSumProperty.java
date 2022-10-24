// https://www.codingninjas.com/codestudio/problems/childrensumproperty_790723?source=youtube&amp;campaign=Striver_Tree_Videos&amp;utm_source=youtube&amp;utm_medium=affiliate&amp;utm_campaign=Striver_Tree_Videos&leftPanelTab=0

//https://www.youtube.com/watch?v=fnmisPM6cVo&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=30

class Solution {
    private static BinaryTreeNode max = null;

    public static void changeTree(BinaryTreeNode root) {
        // Write your code here.
        max = root;
        getMaxNode(root);
        makeLeafMax(root);
        changify(root);
    }

    private static Integer changify(BinaryTreeNode root) {
        if (root == null)
            return null;
        if (isLeaf(root))
            return root.data;

        int left = 0;
        if (root.left != null) {
            left = changify(root.left);
        }
        int right = 0;
        if (root.right != null) {
            right = changify(root.right);
        }
        root.data = left + right;
        return root.data;
    }

    private static BinaryTreeNode getMaxNode(BinaryTreeNode root) {
        if (root == null)
            return null;
        if (root.data > max.data) {
            max = root;
        }
        getMaxNode(root.left);
        getMaxNode(root.right);
        return max;
    }

    private static boolean isLeaf(BinaryTreeNode root) {
        if (root.left == null && root.right == null)
            return true;
        else
            return false;
    }

    private static void makeLeafMax(BinaryTreeNode root) {
        if (root == null)
            return;
        if (isLeaf(root)) {
            root.data = max.data;
        }
        makeLeafMax(root.left);
        makeLeafMax(root.right);
    }
}

class BinaryTreeNode {
    int data;
    BinaryTreeNode left;
    BinaryTreeNode right;

    public BinaryTreeNode(int data) {
        this.data = data;
    }
}