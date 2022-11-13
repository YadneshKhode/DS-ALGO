
//https://www.codingninjas.com/codestudio/problems/floor-from-bst_920457?source=youtube&campaign=Striver_Tree_Videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=Striver_Tree_Videos&leftPanelTab=1

public class FloorAndCeilInBST {
    public static int floorInBST(TreeNode<Integer> node, int x) {
        int ans = Integer.MIN_VALUE;
        while (node != null) {
            /*
             * we can remove below condition, because in else part it would be handled
             * if (node.data == x)
             * return x;
             */

            if (node.data > x) {
                node = node.left;
            } else {
                /*
                 * since we need to find floor (it should be smaller than 'x' but as close to x
                 * as possible so for e.g. x is 8 and i have 2 options 6 and 7 both are less but
                 * 7 is closer to 8 and hence floor of 8 is 7, if i am able to find 8 then i
                 * will choose 8)
                 */
                ans = Math.max(ans, node.data);
                node = node.right;
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // https://www.codingninjas.com/codestudio/problems/ceil-from-bst_920464?source=youtube&campaign=Striver_Tree_Videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=Striver_Tree_Videos&leftPanelTab=1

    public static int findCeil(TreeNode<Integer> node, int x) {
        // Write your code here
        int ans = Integer.MAX_VALUE;
        while (node != null) {
            /*
             * we can remove below condition, and add >= in the if condition
             * if (node.data == x)
             * return x;
             * 
             * if we are having above condition then below can be if (node.data > x)
             */
            if (node.data >= x) {
                /*
                 * since we need to find ceil (it should be greater than 'x' but as close to x
                 * as possible so for e.g. x is 8 and i have 2 options 9 and 10 both are greater
                 * than 'x' but
                 * 9 is closer to 8 and hence ceil of 8 is 9, if i am able to find 8 then i
                 * will choose 8)
                 */
                ans = Math.min(ans, node.data);
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}

class TreeNode<T> {
    public T data;
    TreeNode<T> left;
    TreeNode<T> right;

    TreeNode(T data) {
        this.data = data;
        left = null;
        right = null;
    }
};
