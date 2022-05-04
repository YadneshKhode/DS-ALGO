// https://practice.geeksforgeeks.org/problems/bfs-traversal-of-graph/1#
// wont work on above website since above only checks for graphs starting from 0 and reachable from 0
import java.util.*;

class Solution {
    // Function to return Breadth First Traversal of given graph.
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        ArrayList<Integer> res = new ArrayList<>();
        boolean vis[] = new boolean[V];
        
        for(int i = 0; i < V; i++){
            if(vis[i] == false){
                deque.addLast(i);
                vis[i] = true;
            }
            
            while(!deque.isEmpty()){
                Integer popped = deque.pollFirst();
                 res.add(popped);
                for(Integer x : adj.get(popped)){
                    
                    if(vis[x] == false){
                       vis[x] = true;
                       deque.addLast(x);
                    }
                }
                
            }
            
        }
        
        return res;
    }
}
