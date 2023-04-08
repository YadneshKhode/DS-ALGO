import java.util.*;

//https://www.youtube.com/watch?v=0FtVY6I4pB8&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=25

//12:09 timestamp in video - why we should not use recursion and other traversals

//This problem can only be solved using level order traversal
class Solution {
    public static ArrayList<Integer> bottomView(BinaryTreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (root == null)
            return res;
        Deque<Pair> deque = new ArrayDeque<>();

        deque.addLast(new Pair(root, 0));
        TreeMap<Integer, Integer> map = new TreeMap<>();
        while (!deque.isEmpty()) {
            Pair curr = deque.removeFirst();
            map.put(curr.vertical, curr.node.val);
            if (curr.node.left != null)
                deque.addLast(new Pair(curr.node.left, curr.vertical - 1));
            if (curr.node.right != null)
                deque.addLast(new Pair(curr.node.right, curr.vertical + 1));
        }

        for (Integer i : map.values()) {
            res.add(i);
        }

        return res;
    }
}

class Pair {
    BinaryTreeNode node;
    int vertical;

    public Pair(BinaryTreeNode node, int vertical) {
        this.node = node;
        this.vertical = vertical;
    }
}

class BinaryTreeNode {
    int val;
    BinaryTreeNode left;
    BinaryTreeNode right;

    BinaryTreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}