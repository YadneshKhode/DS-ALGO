
//https://www.youtube.com/watch?v=fmflMqVOC7k&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=29
//https://www.interviewbit.com/problems/path-to-given-node/

/*
 * 1. Perform inorder traversal.
 * 2. Keep going left and keep adding curr node in the resultList until we either encounter the node that we want to find or null node.
 * 3. If we find null then return false, if both children return false then remove current node from list (will be last element in the resultList) and return false.
 * 4. If we find the node that we are searching for then return true, but this time dont remove it from the resultList as we want it in our answer
 */

import java.util.*;

class Solution {
    public int[] solve(TreeNode A, int B) {
        List<Integer> arr = new ArrayList<>();
        getPath(A, B, arr);
        return arr.stream().mapToInt(i -> i).toArray();
    }

    public boolean getPath(TreeNode root, int key, List<Integer> res) {
        if (root == null)
            return false;
        res.add(root.val);
        if (root.val == key)
            return true;

        if (getPath(root.left, key, res) || getPath(root.right, key, res))
            return true;

        res.remove(res.size() - 1);
        return false;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
        left = null;
        right = null;
    }
}
