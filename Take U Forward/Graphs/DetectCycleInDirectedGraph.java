import java.util.ArrayList;

public class DetectCycleInDirectedGraph {
    class Solution {
        // Function to detect cycle in a directed graph.
        public boolean isCyclic(int v, ArrayList<ArrayList<Integer>> adj) {
            boolean visPath[] = new boolean[v];
            boolean vis[] = new boolean[v];

            for (int i = 0; i < v; i++) { // v
                if (vis[i] == false) {
                    // think about making vis as true
                    if (dfs(i, v, adj, vis, visPath) == true) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean dfs(int currNode, int v, ArrayList<ArrayList<Integer>> adj, boolean vis[], boolean visPath[]) {
            vis[currNode] = true;
            visPath[currNode] = true;

            for (Integer currAdjNode : adj.get(currNode)) {

                /*
                 * 1. If we replace if condition with below if condition it causes TLE on GFG
                 * because visPath[currAdjNode] == false causes extra DFS calls.
                 * "if (vis[currAdjNode] == false || visPath[currAdjNode] == false)"
                 * 
                 * 2. If vis[currAdjNode] is true means the node was visited in current / some
                 * other path traversal then in else condition we just check if it is the
                 * current path or not if
                 * yes then there is cycle.
                 * 
                 * 3. But if "vis[currAdjNode] == false" holds false that means it was visited
                 * in some other path but not in current path why are we not traversing this
                 * node in this path? Because if we did not find any cycle in the other path
                 * from this node we won't find it here as well, how we know this? because if
                 * there was a cycle we would have returned true then and there and we wouldn't
                 * reach this state
                 * 
                 * 
                 */
                if (vis[currAdjNode] == false) {
                    if (dfs(currAdjNode, v, adj, vis, visPath) == true) {
                        return true;
                    }
                } else {
                    if (visPath[currAdjNode] == true) {
                        return true;
                    }
                }
            }
            visPath[currNode] = false;
            return false;
        }
    }
}
