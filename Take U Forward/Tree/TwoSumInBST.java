
//https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
//https://www.youtube.com/watch?v=ssL3sHwPeb4&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=60
import java.util.*;

public class TwoSumInBST {

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

    /*
     * 1. Iterate whole tree in inorder traversal method and store in arraylist it
     * would be automatically sorted since BST and inorder
     * 
     * 2. now just use 2pointer method and traverse array finding 2 sum, if sum is
     * less increment p1 and if sum is greater then decrement p2
     */
    class Method1 {
        private List<Integer> res = new ArrayList<>();

        public boolean findTarget(TreeNode root, int k) {
            inorderTraversal(root);
            int p1 = 0, p2 = res.size() - 1;
            while (p1 < p2) { // strictly less than because consider scenario tree is [1] k = 2 sum would be 1
                              // + 1 since pl + p2 = sum
                int sum = res.get(p1) + res.get(p2);
                if (sum == k)
                    return true;
                else if (sum < k) {
                    p1++;
                } else {
                    p2--;
                }
            }
            return false;
        }

        private void inorderTraversal(TreeNode root) {
            if (root == null)
                return;
            inorderTraversal(root.left);
            res.add(root.val);
            inorderTraversal(root.right);
        }
    }

    class Method2 {

        /*
         * 1. We use the BSTIterator method here but we do it for both left as well as
         * right.
         * 2. In normal BSTIterator(left) we follow Inorder (left,root,right) traversal,
         * but in BSTIterator(right) we follow exact opposite (right,root,left)
         * 3. Once both Iterators are setup the left iterator will give us the smallest
         * element in the tree and right iterator will give us the largest element in
         * the tree and now it is simple 2 sum problem
         */
        public boolean findTarget(TreeNode root, int k) {
            BSTIterator left = new BSTIterator(root, false);
            BSTIterator right = new BSTIterator(root, true);

            int leftVal = left.next();
            int rightVal = right.next();
            // Normal 2 sum
            while (leftVal < rightVal) {
                int sum = leftVal + rightVal;
                if (sum == k)
                    return true;
                if (sum < k)
                    leftVal = left.next();
                else
                    rightVal = right.next();
            }
            return false;
        }
    }

    class BSTIterator {
        Deque<TreeNode> deque = new ArrayDeque<>();
        boolean isReverse;

        /*
         * isReverse is needed to know which travesal it is if isReverse is false it is
         * ordinary inorder traversal else it is opposite of inorder traversal
         * (right,root,left)
         */

        public BSTIterator(TreeNode root, boolean isReverse) {
            this.isReverse = isReverse;
            pushAll(root, isReverse);
        }

        /*
         * 1. The inspiration comes from recursive method of inorder traversal AND
         * BSTIterator question
         * 2. if root is null then stop
         * 3. if root is not null add it in deque and keep moving left
         * 4. when we pop, if that popped node has a right then just go right once and
         * then keep doing same thing We again keep going left.
         * 5. Exact opposite needs to be done for reverse inorder, keep going right,
         * once we reach null stop
         * 6. while popping check if it has left if yes go left once and again keep
         * going right while pushing everything in deque
         * 7. We do this so that the SC is O(H), read BSTIterator question for
         * understanding
         */
        public void pushAll(TreeNode root, boolean isReverse) {
            while (root != null) {
                deque.addLast(root);
                if (isReverse) {
                    root = root.right;
                } else {
                    root = root.left;
                }
            }
        }

        public int next() {
            TreeNode temp = deque.removeLast();
            if (isReverse) {
                pushAll(temp.left, isReverse);
            } else {
                pushAll(temp.right, isReverse);
            }
            return temp.val;
        }

        public boolean hasNext() {
            return !deque.isEmpty();
        }
    }
}
