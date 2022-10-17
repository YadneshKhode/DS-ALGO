import java.util.*;

//https://www.youtube.com/watch?v=q_a6lpbKJdw&list=PLJ_vPQ_vraNz90tiB1HNgUWjivW07RcXC&index=18
//https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/

public class VerticalOrderTraversal {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null)
            return list;
        Deque<Pair> deque = new ArrayDeque<>();
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        deque.addLast(new Pair(root, 0, 0));

        while (!deque.isEmpty()) {
            Pair currPair = deque.removeFirst();

            if (!map.containsKey(currPair.vertical)) {
                map.put(currPair.vertical, new TreeMap<>());
            }

            if (!map.get(currPair.vertical).containsKey(currPair.level)) {
                map.get(currPair.vertical).put(currPair.level, new PriorityQueue<>());
            }

            map.get(currPair.vertical).get(currPair.level).offer(currPair.node.val);

            if (currPair.node.left != null)
                deque.addLast(new Pair(currPair.node.left, currPair.vertical - 1, currPair.level + 1));
            if (currPair.node.right != null)
                deque.addLast(new Pair(currPair.node.right, currPair.vertical + 1, currPair.level + 1));

        }
        for (TreeMap<Integer, PriorityQueue<Integer>> innerMap : map.values()) {
            list.add(new ArrayList<>());
            for (PriorityQueue<Integer> pq : innerMap.values()) {
                while (!pq.isEmpty()) {
                    list.get(list.size() - 1).add(pq.poll());
                }
            }
        }
        return list;
    }
}

class Pair {
    int vertical;
    int level;
    TreeNode node;

    public Pair(TreeNode node, int vertical, int level) {
        this.vertical = vertical;
        this.node = node;
        this.level = level;
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