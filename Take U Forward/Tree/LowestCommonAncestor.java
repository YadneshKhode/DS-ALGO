
//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
//https://www.youtube.com/watch?v=_-QHfMDde90&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=29
import java.util.*;

public class LowestCommonAncestor {
    /*
     * Solution 1 -
     * this technique is similar to question -> PathToGivenNode
     * Just calculate path from root to p and root to q and get the
     * path list then keep iterating until you find the last same node, this node
     * will be lowest common ancestor
     * 
     * TC - O(N) + O(N) = O(N) (1 for 1st path list and 1 for 2nd path list)
     * SC - O(N) + O(N) = O(N) (1 for 1st path list and 1 for 2nd path list)
     * 
     * This can be optimised as below
     * 
     * 1. if curr node is null or it is not p or q (p and q are provided as
     * arguments)return false else return true
     * 2. the node at which we receive "true" from both left child and right child
     * is the answer
     * 3. this technique is also similar to question -> PathToGivenNode
     */

    // * Technique 1: Unoptimised
    public TreeNode solve(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> arr1 = new ArrayList<>();
        List<TreeNode> arr2 = new ArrayList<>();
        getPath(root, p, arr1);
        getPath(root, p, arr2);

        // 1 2 3
        // 1 2 4
        // 2 is answer
        int p1 = 0, p2 = 0;
        while (arr1.get(p1) == arr2.get(p2)) {
            p1++;
            p2++;
        }

        return arr1.get(p1);
    }

    public boolean getPath(TreeNode root, TreeNode key, List<TreeNode> res) {
        if (root == null)
            return false;
        res.add(root);
        if (root == key)
            return true;

        if (getPath(root.left, key, res) || getPath(root.right, key, res))
            return true;

        res.remove(res.size() - 1);
        return false;
    }

    // * Technique 2: Optimised

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        /*
         * 1.We cannot use if(left != null) return left; and same for right because then
         * if it is not null then it will immediately return left and we dont want that
         * 
         * 2. if left is actually null then return right (it could be null or any other
         * value)
         * 3. if left is not null then we could be on our potential answer
         * "lowestCommonAncestor" node here we need to check if right is null or not if
         * right is null then directly return left as current node is NOT a
         * lowestCommonAncestor, because below this node we are not able to find "BOTH"
         * the required nodes(p and q) and hence the lowestCommonAncestor must be above
         * somewhere
         * 4. If both are not null then this is the lowestCommonAncestor since both the
         * required nodes (p and q) lie below this node.
         * 
         * 5. whenever we get lowestCommonAncestor we return its value and above
         * lowestCommonAncestor node we keep returning non-null value (value among left
         * or right child whichever is non-null is returned) that is
         * lowestCommonAncestor and the
         * other child will always keep returning null and hence at the end we will get
         * lowestCommonAncestor as our final answer
         * 
         * TC - O(N)
         * SC - O(N) - in case of skew tree else the height of the tree (log n)
         * 
         * 
         * in short -
         * Solution approach:- Use DFS traversal(Recursive DFS) first go to left and
         * then go to right.
         * 0) If the root node is only one the node which you are looking for then
         * return root
         * 1) If both left and right returns null then returns null
         * 2) If left returns a node and right returns null then return left and vice
         * versa (Return something which gives u node)
         * 3) If both returns you the nodes then u have found the answer so return root
         * 
         */
        if (left == null)
            return right;
        else if (right == null)
            return left;
        else
            return root;
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
