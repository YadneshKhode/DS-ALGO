// * Link - https://leetcode.com/problems/maximum-width-of-binary-tree/
// ? https://www.youtube.com/watch?v=ZbybYvcVLks&list=PLJ_vPQ_vraNz90tiB1HNgUWjivW07RcXC&index=26

/*
 * 1. our plan is to assign numbers to each node and then calculate number of last node of the level - number of first node of the level + 1 will give the number of nodes in that level... we need max of all levels
 * 
 * 2. remember in heap tree (priorityqueue) when we convert it to array how do we place the nodes?
 *  root is placed on 0th index and its left child is placed on 1st index and right child on 2nd index
 *  in other words if i = 0 then left child is on (2 * i) + 1 and right child is on (2 * i) + 2
 * 
 * 3. Similar strategy is used here ... but we can see for e.g. currentNode(root) is on 50th index then its left child will be on 101st index..and right node on 102nd ... this has huge space requirement and our int (32bit) will overflow easily.
 * 
 * 4. Hence we need a solution that keeps track of 1st node number and last node number of the curr level and we can calculate the number of nodes in between easily.
 * 
 * 5. so to reduce the size what we can do is subtract the node number of the 1st node (since this node will always have the minimum value of that level) in that particular level from all node numbers in that level.
 * 
 * for eg. on level 2 (level 1 is the root of the tree) the 1st node has value 1 (since 2 * 0 + 1 = 1) and 2nd node has value 2 (since 2 * 0 + 2 = 2), now we subtract the minimum node number on this level from each and every node, we can clearly see that the first node on each level will always have minimum value and hence
 * now 1st node has value 0 (1 - (value of 1st node) = 1-1 = 0) and 2nd node has value 1 (2 (value of 2nd node) - (value of 1st node (minimum node)) = 2-1 = 1)
 * 
 * 6. Now value of last node - first node + 1 will give number of nodes in a level -> 2 - 1 + 1 = 2 nodes on level 2
 * 
 * 7. when there are many nodes on each level we need to store this 1st value and last value of node on each level and once all nodes on a level are processed we need to get previousMaxAns = Max(previousMaxAns, lastNode - 1stNode + 1)
 * 
 * 8. Do this for each level and in the end return previousMaxAns.
 */

import java.util.*;

class Solution {
    public int widthOfBinaryTree(TreeNode root) {

        if (root == null)
            return 0;

        int ans = 0;
        Deque<Pair> deque = new ArrayDeque<>();

        deque.addLast(new Pair(root, 0));

        while (!deque.isEmpty()) {
            int size = deque.size();
            /*
             * So that we can track what is the minimum node number of current level,
             * We are adding on the deque from back and removing from the front
             * hence minOfCurrLevel value will be of the 1st node in that particular level
             */
            int minOfCurrLevel = deque.peek().num;
            int leftNum = 0, rightNum = 0;

            // * Need below for-loop to keep track when 1 particular level of level-order
            // * traversal is completed
            for (int i = 0; i < size; i++) {
                Pair curr = deque.removeFirst();
                /*
                 * We are removing nodes 1 by 1 from front, currNumber is the value after
                 * adjustment for the int overflow
                 * now this readjusted currNumber will be used in the formula (2 * i + 1) to
                 * calculate left node number and same for right node number of the children of
                 * currently removed node.
                 */
                int currNumber = curr.num - minOfCurrLevel;
                // * Storing to calculate number of nodes on curr level
                if (i == 0)
                    leftNum = currNumber;
                if (i == size - 1)
                    rightNum = currNumber;
                if (curr.node.left != null)
                    deque.addLast(new Pair(curr.node.left, (currNumber << 1) + 1));
                if (curr.node.right != null)
                    deque.addLast(new Pair(curr.node.right, (currNumber << 1) + 2));
            }
            // * Keeping track of overall max of nodes on a level
            ans = Math.max(ans, rightNum - leftNum + 1);

        }

        return ans;
    }

    public int widthOfBinaryTreeOld(TreeNode root) {

        Deque<Pair> deque = new ArrayDeque<>();
        deque.addLast(new Pair(root, 0));
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, overallMax = Integer.MIN_VALUE;
        while (!deque.isEmpty()) {
            int size = deque.size();

            while (size != 0) {
                Pair curr = deque.removeFirst();
                size--;
                min = Math.min(curr.num, min);
                max = Math.max(curr.num, max);
                overallMax = Math.max(overallMax, 1 + max - min);
                int leftAdd = ((curr.num) << 1) + 1;
                int rightAdd = ((curr.num) << 1) + 2;
                if (curr.node.left != null)
                    deque.addLast(new Pair(curr.node.left, leftAdd));
                if (curr.node.right != null)
                    deque.addLast(new Pair(curr.node.right, rightAdd));
            }
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;

        }

        return overallMax;
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

class Pair {
    TreeNode node;
    int num;

    public Pair(TreeNode node, int num) {
        this.node = node;
        this.num = num;
    }
}