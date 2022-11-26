
//https://leetcode.com/problems/binary-search-tree-iterator/
//https://www.youtube.com/watch?v=D2jMcmxU4bs&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=59
import java.util.*;

public class BstIterator {

    /*
     * Method 1 - Iterate whole tree in inorder manner and fill the arraylist and
     * then just return next or check for hasnext
     */

    /*
     * Implementation -
     * 
     * private List<Integer> arr = new ArrayList<>();
     * private int currpointer = 0;
     * 
     * public BstIterator(TreeNode root) {
     * fillTheArr(root);
     * }
     * 
     * private void fillTheArr(TreeNode root) {
     * if (root == null)
     * return;
     * fillTheArr(root.left);
     * arr.add(root.val);
     * fillTheArr(root.right);
     * }
     * 
     * public int next() {
     * return arr.get(currpointer++);
     * }
     * 
     * public boolean hasNext() {
     * return currpointer < arr.size();
     * }
     * 
     */

    /*
     * Method 2:
     * 1. In recursion we keep going left, then root and then we go right once and
     * then again keep going left.
     * 
     * 
     * SC - O(H) - at any given time the max elements in deque will be the height of
     * the tree - we can use morris traversal as well to make SC-O(1)
     * 
     * TC - O(1) on average - storing in stack is not considered as it is
     * preprocessing and amortised complexity is O(1). since we are not pushing on
     * stack
     * each call we push once and pop once for each operation we are doing this once
     * on average - 12:00 min on video
     * 
     */
    Deque<TreeNode> deque = new ArrayDeque<>();

    public BstIterator(TreeNode root) {
        pushAll(root);
    }

    public int next() {
        TreeNode temp = deque.removeLast();
        pushAll(temp.right);
        return temp.val;
    }

    private void pushAll(TreeNode node) {
        while (node != null) {
            deque.addLast(node);
            node = node.left;
        }
    }

    public boolean hasNext() {
        return !deque.isEmpty();
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
