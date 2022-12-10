import java.util.*;

//https://www.youtube.com/watch?v=Qzf1a--rhp8&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=9
//https://practice.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=dfs_of_graph
public class DfsTraversal {
    /*
     * get 1st vertice, we have the adj list for this vertice call recursion on each
     * vertice in the adj list if it is not visited
     * 
     * SC - O(N){we are visiting N nodes once} + O(N){space required to store
     * visited array} + O(N) {recursion stack space - what if graph is skewed hence
     * O(N)} = O(3N) = O(N)
     * 
     * 
     * TC - Recursive function is called once every node and we iterate all the adj
     * list for a certain node (can also be called as degree of the graph)
     * 
     * - TC will be summation of degree of all nodes = 2 x No. of Edges and we are
     * visiting each node once and calling the recursive function once for each node
     * 
     * hence TC = (2 x No. of Edges) + O(N)
     * 
     */
    public ArrayList<Integer> dfsOfGraph(int v, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        Boolean vis[] = new Boolean[v];
        Deque<Integer> deque = new ArrayDeque<>();
        ArrayList<Integer> dfs = new ArrayList<>();
        Arrays.fill(vis, false);
        deque.addLast(0);
        dfsOfGraph(v, adj, vis, deque, dfs);
        return dfs;
    }

    public void dfsOfGraph(int v, ArrayList<ArrayList<Integer>> adj, Boolean vis[], Deque<Integer> deque,
            ArrayList<Integer> dfs) {
        Integer currNode = deque.removeFirst();
        vis[currNode] = true;
        dfs.add(currNode);
        for (Integer i : adj.get(currNode)) {
            if (vis[i] == false) {
                vis[i] = true;
                deque.addLast(i);
                dfsOfGraph(v, adj, vis, deque, dfs);
            }
        }
    }

}
