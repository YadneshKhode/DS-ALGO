
//https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
//https://www.youtube.com/watch?v=-YbXySKJsX8&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=38
import java.util.*;

public class SerialiseAndDeserialiseTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)
            return "";
        StringBuilder sb = new StringBuilder();
        /*
         * we cannot have null elements in array deque so we need to either use another
         * data structure like queue or find some workaround to accomodate null
         */
        Deque<TreeNode> deque = new ArrayDeque<>();

        deque.addLast(root);

        while (!deque.isEmpty()) {
            TreeNode curr = deque.removeFirst();
            /*
             * 1. 1001 because the range of treenode val is from -1000 to 1000,
             * this 1001 represents 'null' in the arraydeque since null is prohibited in
             * arraydeque
             * 
             * 2. If current treenode's value is null then we are appending 'n' to our
             * string
             * 'n' represents null in the string
             * 
             * [1,2,3,null,null,4,5] -> This is the level order traversal of the tree
             * 
             * 3. We are doing normal level order traversal and generating the string, just
             * instead of 'null' we have 'n' in the string.
             * 
             * 4. The only exception is usually in normal level order traversal we refrain
             * from adding null to ArrayDeque but since here while generating string we need
             * to keep track of the null so we can append 'n' we have to add null to the
             * ArrayDeque, since null is not allowed we are creating a node with value that
             * is out of bounds since given input range is between -1000 to 1000.
             */

            if (curr.val == -1001) {
                sb.append("n,");
                continue;
            }

            sb.append(curr.val + ",");
            // we pass even if it is null since it will be handled
            if (curr.left == null) {
                deque.addLast(new TreeNode(-1001));
            } else {
                deque.addLast(curr.left);
            }

            if (curr.right == null) {
                deque.addLast(new TreeNode(-1001));
            } else {
                deque.addLast(curr.right);
            }

        }
        /*
         * deleting the last ',' added
         */
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "")
            return null;
        String arr[] = data.split(",");
        Deque<TreeNode> deque = new ArrayDeque<>();
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));

        /*
         * This is the string we had received and converted to array ->
         * [1,2,3,'n','n',4,5]
         * 
         * Now we will do same thing as level order traversal
         * 
         * 1. Now 1 is the root 2 is its left node and 3 is its right node,
         * 
         * we have already added 1 to the arraydeque, so we are running loop from 1 to
         * arr.length, now we pop the first element and append the current element
         * pointed by i to the left of popped treenode (we are popping integer but then
         * creating a new treenode using this integer)
         * 
         * 2. Now we do ++i, also get the right element (3 in this case) and append to
         * the right of popped node
         * 
         * 3. Now current arraydeque = (2,3)
         * we pop 2 and our i = 3 (since once it was incremented before appending right
         * node and once it as incremmented in the for loop, so i will jump from i = 1
         * to directly i = 3)
         * 
         * now just repeat above, if arr[i] is 'n' just ignore and move on since it will
         * automatically null
         * 
         * 
         */

        deque.addLast(root);
        /*
         * We can run loop until arr.length or arr.length-1 it makes no difference since
         * we are doing ++i for right node it gets covered even when we are on second
         * last element in array, when we are on second last element we do ++i and also
         * add the last element in the arraydeque
         */
        for (int i = 1; i < arr.length - 1; i++) {
            TreeNode current = deque.removeFirst();
            if (!arr[i].equals("n")) {
                TreeNode newNode = new TreeNode(Integer.parseInt(arr[i]));
                current.left = newNode;
                deque.addLast(newNode);
            }

            /*
             * Remember we are comparing strings and hence cant use '=='
             * 
             * instead of incrementing 'i' in below if condition we can separately increment
             * 'i'
             * 
             * i+=1
             */
            if (!arr[++i].equals("n")) {
                /*
                 * why are we passing i instead of ++i while creating TreeNode because the i is
                 * already incremented in the 'if' condition to avoid confusion we can
                 * separately increment i, i+=1 before checking for 2nd if condition above
                 */
                TreeNode newNode = new TreeNode(Integer.parseInt(arr[i]));
                current.right = newNode;
                deque.addLast(newNode);
            }
        }
        return root;
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