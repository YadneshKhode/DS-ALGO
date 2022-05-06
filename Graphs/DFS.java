package Graphs;

import java.util.*;
//GFG - https://practice.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1#

public class DFS {
    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] vis = new boolean[V];
        ArrayList<Integer> res = new ArrayList<Integer>();
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();

        // remove for loop to run on gfg for loop also covers disconnected graph
        // if we dont want to include disconnected graph then comment out the whole
        // below for loop and replace it with following -

        /*
         * // this will start traversal from 0 node and visit nodes that are reachable
         * only from 0th node
         * // rest of the nodes that are not reachable from 0 won't be visited
         * 
         * vis[0] = true;
         * deque.addLast(0);
         * dfs(V,adj,res,vis,deque);
         */

        for (int i = 0; i < V; i++) {
            if (vis[i] == false) {
                vis[i] = true;
                deque.addLast(i);
                dfs(V, adj, res, vis, deque);
            }

        }

        return res;

    }

    public void dfs(int V, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> res, boolean[] vis,
            ArrayDeque<Integer> deque) {

        while (!deque.isEmpty()) {
            int top = deque.removeLast();
            res.add(top);
            for (int i = 0; i < adj.get(top).size(); i++) {
                Integer x = adj.get(top).get(i);
                if (vis[x] == false) {
                    deque.addLast(x);
                    vis[x] = true;
                    dfs(V, adj, res, vis, deque);
                }
            }

        }
    }
}
