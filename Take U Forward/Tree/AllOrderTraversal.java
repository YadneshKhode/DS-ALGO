import java.util.*;

//https://www.youtube.com/watch?v=ySp2epYvgTE&list=PLJ_vPQ_vraNz90tiB1HNgUWjivW07RcXC&index=18

/*
 * 
 * Algorithm
 * 
 * 
 * 1. Maintain a stack with Pairs of root and value (1,2, or 3)
 * 2. push root element with 1 as value in the stack and then begin the while loop
 * 3. pop from stack 
 * 4. check if value is either 1 or 2 or 3
 * 5. 1 denotes this currentNode is going to be added in preorder traversal result list so on and so forth for 2 and 3 for inorder and postOrder traversal.
 * 6. if current val is 1 then add the current root to preorder list and increment the val to 2 and reinsert in the stack then check if the node has left or not if yes then push it on stack with value 1
 * 7. similarly for 2, but here we need to check if the currNode has right node or not since we already checked left node previously.
 * 
 * 8. In postorder everything is already processed and hence just add in postorder list and move on.
 * 
 * 
 * notes from comments
 * 
 * 1. for preorder(we print the node when we touch for the first time,before touching left and right node),for inorder we print the node when we touch it for second time(after touching left node),for post order we print the node when we meet it for third time(after meeting left and right)..
 * 
 * 2. 
 */

class AllOrderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> preOrder = new ArrayList<>();
        List<Integer> inOrder = new ArrayList<>();
        List<Integer> postOrder = new ArrayList<>();
        if (root == null)
            return postOrder;
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, 1));
        // Stack<TreeNode> stack2 = new Stack<>();

        // stack1.push(root);

        // while(!stack1.isEmpty()){
        // root = stack1.pop();
        // stack2.push(root);
        // if(root.left != null) stack1.push(root.left);
        // if(root.right != null) stack1.push(root.right);

        // }

        // while(!stack2.isEmpty()) postOrder.add(stack2.pop().val);

        while (!stack.isEmpty()) {
            Pair currPair = stack.pop();
            if (currPair.num == 1) {
                preOrder.add(currPair.node.val);
                ++currPair.num;
                stack.push(currPair);
                if (currPair.node.left != null)
                    stack.push(new Pair(currPair.node.left, 1));
            } else if (currPair.num == 2) {
                inOrder.add(currPair.node.val);
                ++currPair.num;
                stack.push(currPair);
                if (currPair.node.right != null)
                    stack.push(new Pair(currPair.node.right, 1));
            } else {
                postOrder.add(currPair.node.val);
            }
        }
        System.out.println(preOrder);
        System.out.println(inOrder);
        return postOrder;
    }
}

class Pair {
    TreeNode node;
    int num;

    Pair(TreeNode nodex, int numx) {
        this.node = nodex;
        this.num = numx;
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
    }
}