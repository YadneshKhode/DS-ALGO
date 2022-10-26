
//https://www.youtube.com/watch?v=2r5wLmQfD6g&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=32
import java.util.*;

public class TimeToBurnBinaryTreeFromAnyNode {
    /*
     * We can use exact same code as ->
     * Print_All_The_Nodes_At_a_Distance_Of_K_in_Binary_Tree.java
     * just keep going the loop (k) until all nodes are not visited and whatever is
     * the value of k is the answer
     * 
     * OR use my method
     * 
     * 1. Calculate parent of each node store in map, whichever is the targetNode
     * (the node from which burning will begin) treat it as a root and just
     * calculate the height of the tree while keeping track of visited nodes
     * 
     */

    public static int timeToBurnTree(BinaryTreeNode root, Integer start) {
        if (root == null)
            return 0;
        HashMap<BinaryTreeNode, BinaryTreeNode> parentMap = new HashMap<>();
        BinaryTreeNode target = fillParentMapAndGetTarget(root, parentMap, start);
        HashSet<BinaryTreeNode> visited = new HashSet<>();
        return calculateHeight(target, parentMap, visited);
    }

    private static int calculateHeight(BinaryTreeNode root, HashMap<BinaryTreeNode, BinaryTreeNode> parentMap,
            HashSet<BinaryTreeNode> visited) {
        if (root == null)
            return 0;
        visited.add(root);
        int left = 0, right = 0, parent = 0;
        if (!visited.contains(root.left))
            left = calculateHeight(root.left, parentMap, visited);
        if (!visited.contains(root.right))
            right = calculateHeight(root.right, parentMap, visited);
        if (!visited.contains(parentMap.get(root)))
            parent = calculateHeight(parentMap.get(root), parentMap, visited);

        return 1 + Math.max(left, Math.max(right, parent));
    }

    private static BinaryTreeNode fillParentMapAndGetTarget(BinaryTreeNode root,
            HashMap<BinaryTreeNode, BinaryTreeNode> parentMap, Integer start) {
        Deque<BinaryTreeNode> deque = new ArrayDeque<>();
        BinaryTreeNode target = null;
        deque.addLast(root);
        while (!deque.isEmpty()) {
            BinaryTreeNode curr = deque.removeFirst();
            if (curr.data == start)
                target = curr;
            if (curr.left != null) {
                deque.addLast(curr.left);
                parentMap.put(curr.left, curr);
            }
            if (curr.right != null) {
                deque.addLast(curr.right);
                parentMap.put(curr.right, curr);
            }
        }
        return target;
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