
//https://www.youtube.com/watch?v=i9ORlEy6EsI&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=32
//https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
import java.util.*;

/*
 * 1. We need a way to traverse to the parent of the current node as well and hence we are going to traverse whole tree in level-order traversal manner and store each node's parent in a HashMap.
 * 
 * 2. Now just do a Level order traversal from *TARGET* node to its left child, right child and the parent node.
 * 
 * 3. Repeat above step 'K' times
 * 
 * 4. In the end the elements remaining inside the queue are the elements at the 'K' distance.
 * 
 * 
 * TC - O(N) + O(N) = O(N)
 *    - 1 Traversal to mark parents and another to get the answer
 * 
 * SC - O(N) + O(N) + O(N) == O(N)
 *    - we are saving parent node of each and every node + We are saving nodes on each level inside the deque (This is usually 2 ^ n-1 where n is the height of the tree ) + Space complexity of storing visited nodes 
 * 
 * Hashmap has O(1) complexity in Java and O(log n) in C++
 */
public class Print_All_The_Nodes_At_a_Distance_Of_K_in_Binary_Tree {
    private void markParents(TreeNode root, Map<TreeNode, TreeNode> parentMap) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addFirst(root);

        while (!deque.isEmpty()) {
            TreeNode curr = deque.removeFirst();
            if (curr.left != null) {
                parentMap.put(curr.left, curr);
                deque.addLast(curr.left);
            }
            if (curr.right != null) {
                parentMap.put(curr.right, curr);
                deque.addLast(curr.right);
            }
        }

    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> result = new ArrayList<>();

        if (root == null)
            return result;

        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        Map<TreeNode, Boolean> visited = new HashMap<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        markParents(root, parentMap);

        deque.addLast(target);
        visited.put(target, true);

        while (k != 0) {

            int size = deque.size();
            k--;
            /*
             * size is needed so that k is updated only after all nodes on that level are
             * visited
             * "visited.get(parentNodeOfCurrNode) == null" -> This means that there is no
             * entry in the map,
             * whenever there is no entry present in the map it returns null, and remember
             * there is no coercion in java so this null is not coerced into "false" as in
             * Javascript
             */
            for (int i = 0; i < size; i++) {
                TreeNode curr = deque.removeFirst();
                if (curr.left != null && visited.get(curr.left) == null) {
                    deque.addLast(curr.left);
                    visited.put(curr.left, true);
                }
                if (curr.right != null && visited.get(curr.right) == null) {
                    deque.addLast(curr.right);
                    visited.put(curr.right, true);
                }
                // * "parentMap.get(curr)" this is the parent node of the current node
                TreeNode parentNodeOfCurrNode = parentMap.get(curr);
                if (parentNodeOfCurrNode != null && visited.get(parentNodeOfCurrNode) == null) {
                    deque.addLast(parentNodeOfCurrNode);
                    visited.put(parentNodeOfCurrNode, true);
                }
            }
        }

        while (!deque.isEmpty()) {
            result.add(deque.removeFirst().val);
        }

        return result;

    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}