//https://practice.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=detect-cycle-in-an-undirected-graph

//https://takeuforward.org/data-structure/detect-cycle-in-an-undirected-graph-using-dfs/

//Time Complexity: O(N + 2E) + O(N), Where N = Nodes, 2E is for total degrees as we traverse all adjacent nodes. In the case of connected components of a graph, it will take another O(N) time.

//Space Complexity: O(N) + O(N) ~ O(N), Space for recursive stack space and visited array.
import java.util.*;

public class DetectCycle {
    // Function to detect cycle in an undirected graph.
    private boolean dfs(int node, int parent, int vis[], ArrayList<ArrayList<Integer>> adj) {
        vis[node] = 1;
        // go to all adjacent nodes
        for (int adjacentNode : adj.get(node)) {
            if (vis[adjacentNode] == 0 && (dfs(adjacentNode, node, vis, adj))) {
                return true;
            }
            // if adjacent node is visited and is not its own parent node
            else if (adjacentNode != parent)
                return true;
        }
        return false;
    }

    // Function to detect cycle in an undirected graph.
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        int vis[] = new int[V];
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0 && dfs(i, -1, vis, adj)) {
                return true;
            }
        }
        return false;
    }
}
