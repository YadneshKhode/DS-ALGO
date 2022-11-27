
//https://leetcode.com/problems/recover-binary-search-tree/
//https://www.youtube.com/watch?v=ZWGW7FminDM&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=62

public class RecoverBST {
    /*
     * Method-1 - not implemented here
     * 1. iterate whole tree using any order traversal and store in array and sort
     * the array.
     * 2. Iterate tree in Inorder traversal as it will be in sorted this way keep a
     * pointer in array and keep checking if curr pointer in array is equal to the
     * tree node's value if yes keep moving further
     * 3.If not then make the curr treenode's value same as the pointer in the array
     * and move forward
     */

    /*
     * Method-2
     * SC - O(1) if we use morris traversal
     * TC - O(N) as we iterate tree only once
     * 
     * 1. There can be 2 cases either the value that needs to be swapped are next to
     * each other or they are far apart
     * 2. We iterate the tree in inorder travesal way and keep track of prev node
     * and curr node, initially we make prev node as Integer.MIN_VALUE now we start
     * traversing the tree and keep checking if prev is less than curr node value if
     * yes we make prev = root and go ahead.
     * 3. Once this condition fails we have found the node with issue now we have to
     * check if this issue causing node is the 1st occurance or 2nd
     * 4. If 1st then store prev as 1st occurance and middle as 2nd occurance
     * 5. If we encounter troublesome node once again this time store it in 'second'
     * 6. Now just check if first and second exists swap them else check if first
     * and middle exists and swap them
     */
    class Solution {
        private TreeNode first;
        private TreeNode middle;
        private TreeNode second;
        private TreeNode prev;

        public void recoverTree(TreeNode root) {
            // first = middle = second = null; // can be considered if 1 object is used to
            // call function again and again as the private variables might have values
            prev = new TreeNode(Integer.MIN_VALUE);
            inorder(root);
            if (first != null && second != null) {
                swap(first, second);
            } else {
                swap(first, middle);
            }
        }

        private void inorder(TreeNode root) {
            if (root == null)
                return;
            inorder(root.left);

            if (prev != null && prev.val > root.val) {
                if (first == null) {
                    first = prev;
                    middle = root;
                } else {
                    second = root;
                }
            }
            prev = root;
            inorder(root.right);
        }

        private void swap(TreeNode node1, TreeNode node2) {
            int temp = node1.val;
            node1.val = node2.val;
            node2.val = temp;
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
}
