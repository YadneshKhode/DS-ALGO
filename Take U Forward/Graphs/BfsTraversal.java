//https://www.youtube.com/watch?v=-tgVpUgsQ5k&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=6
//https://practice.geeksforgeeks.org/problems/bfs-traversal-of-graph/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=bfs_of_graph
import java.util.*;
class BfsTraversal {
    // Function to return Breadth First Traversal of given graph.
    public ArrayList<Integer> bfsOfGraph(int v, ArrayList<ArrayList<Integer>> adj) {

        ArrayList<Integer> bfs = new ArrayList<>();
        Boolean vis[] = new Boolean[v];
        // so it wont throw null pointer exception when we try to access indexes that are not already visited
                       
        Arrays.fill(vis, false); 
        Deque<Integer> deque = new ArrayDeque<>();

        vis[0] = true;
        deque.addLast(0);

        // We are not considering disconnected components of the graph
        while (!deque.isEmpty()) {
            Integer currNode = deque.removeFirst();
            bfs.add(currNode);
            for (Integer i : adj.get(currNode)) {
                if (vis[i] == false) {
                    deque.addLast(i);
                    vis[i] = true;
                }
            }
        }
        return bfs;
    }
}