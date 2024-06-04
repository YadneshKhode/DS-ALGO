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

class Solution {
    // Function to detect cycle in an undirected graph.
    private boolean dfs(int node, int parent, int vis[], ArrayList<ArrayList<Integer>> adj) {
        vis[node] = 1;
        // go to all adjacent nodes
        for (int adjacentNode : adj.get(node)) {
            if (vis[adjacentNode] == 0) {
                if (dfs(adjacentNode, node, vis, adj) == true)
                    return true;
            }

            /*
             * 
             * if (vis[adjacentNode] == 0 && (dfs(adjacentNode, node, vis, adj))) {
             * return true;
             * }
             * 
             * why above code is wrong?
             * 
             * vis[adjacentNode] == 0 true zala and dfs(adjacentNode, node, vis, adj)) false
             * zala ata hya case made hya if cha else run honar .. mhanun code hagnar
             * 
             * 
             * why below code is correct?
             * if (vis[adjacentNode] == 0) { true zala tar aatla run honar aat made jhata
             * faraq padat nai kai hotay and hya scenario made hya if cha
             * "else kadi run nai honar"
             * 
             * 
             * 
             * if (vis[adjacentNode] == 0) {
             * if (dfs(adjacentNode, node, vis, adj) == true)
             * return true;
             * }
             * 
             */

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




// Detect Cycle using BFS without recording parent node - just count no. of time adj node of curr node return true
// if it is more than equal to 1 then it means there is some other node except parent node that is already visited.


class Solution {
    // Function to detect cycle in an undirected graph.
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        boolean[] vis = new boolean[V];
        
        for(int i = 0; i < V; i++){
            if(vis[i] == false){
                vis[i] = true;
                deque.addFirst(i);
            }
            
            while(!deque.isEmpty()){
                int curr = deque.removeFirst();
                int count = 0;
                for(int j = 0; j < adj.get(curr).size(); j++){
                    if(vis[adj.get(curr).get(j)] == false){
                        deque.addLast(adj.get(curr).get(j));
                        vis[adj.get(curr).get(j)] = true;
                    }else{
                        count++;
                    }
                    
                    if(count > 1) return true;
                }
                count++;
            }
        }
         return false;
    }
}
