
//https://www.youtube.com/watch?v=-tgVpUgsQ5k&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=6
//https://practice.geeksforgeeks.org/problems/bfs-traversal-of-graph/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=bfs_of_graph
import java.util.*;

class BfsTraversal {
    /*
     * push all adj nodes for the vertice into the queue then visit the 1st vertice
     * in the adj list of curr vertice and again add all adj nodes for the curr node
     * in FIFO manner keep doing this to get BFS
     * 
     * Remember BFS is like Level order traversal all the nodes that are at distance
     * 1 from root needs to be visited 1st and then all nodes at distance of 2 from
     * root needs to be visited so on and so forth. so basically when we are on 0th
     * vertice we push all nodes at level 1 in the deque and when we are at vertice
     * 1 we push all nodes that are at 2nd level
     * 
     * SC - O(3N) - visited array + deque + visit each vertice once = O(N);
     * TC - O(N) + (2 x No. of edges) -> time to iterate each vertice + we are
     * iterating over all neighbors of that certain vertice hence it is equal to
     * summation of all degrees of all vertices in the graph which is equivalent to
     * (2 x No. of edges in the graph)
     */
    public ArrayList<Integer> bfsOfGraph(int v, ArrayList<ArrayList<Integer>> adj) {

        ArrayList<Integer> bfs = new ArrayList<>();
        Boolean vis[] = new Boolean[v];
        // so it wont throw null pointer exception when we try to access indexes that
        // are not already visited

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