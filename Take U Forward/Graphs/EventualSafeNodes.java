import java.util.*;

class EventualSafeNodes {

    List<Integer> eventualSafeNodes(int v, List<List<Integer>> adj) {
        int vis[] = new int[v];
        int[] checkNode = new int[v];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            if (vis[i] == 0) {
                dfs(i, v, adj, checkNode, vis);
            }
        }
        for (int i = 0; i < v; i++) {
            if (checkNode[i] == 1)
                res.add(i);
        }
        return res;
    }

    private boolean dfs(int node, int v, List<List<Integer>> adj, int[] checkNode, int vis[]) {
        // 1 means this node was visited
        // 2 means this node is currently being visited in the path
        vis[node] = 2;
        for (Integer adjNode : adj.get(node)) {
            if (vis[adjNode] == 0) {
                // make sure this node is not added to res
                if (dfs(adjNode, v, adj, checkNode, vis) == true) {
                    checkNode[adjNode] = 0;
                    // vis[adjNode] = 1; we dont do this because this adjNode could be
                    // unsafe now but while traversing another path we could mark this as safe
                    // and that would give wrong answer and hence we keep it as 2 so we know it is
                    // part
                    // of cycle
                    return true;
                }
            } else if (vis[adjNode] == 2) {
                checkNode[adjNode] = 0;
                // vis[adjNode] = 1; we dont do this because this adjNode could be
                // unsafe now but while traversing another path we could mark this as safe
                // and that would give wrong answer and hence we keep it as 2 so we know it is
                // part
                // of cycle
                return true;
            }
        }
        checkNode[node] = 1; // marking as safe node
        vis[node] = 1; // means not a part of cycle AND this is visited and not part of current Path
        return false; // No cycle found
    }
}
