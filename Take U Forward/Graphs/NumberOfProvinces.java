//Time Complexity: O(N) + O(V+2E), Where O(N) is for outer loop and inner loop runs in total a single DFS over entire graph, and we know DFS takes a time of O(V+2E). 

//Space Complexity: O(N) + O(N),Space for recursion stack space and visited array.

import java.util.*;

class NumberOfProvincesBFS {
    static int numProvinces(ArrayList<ArrayList<Integer>> mat, int v) {
        // code here
        Deque<Integer> deque = new ArrayDeque<>();
        int countOfProvinces = 0;
        Boolean vis[] = new Boolean[v];
        /*
         * Initialise whole array as false so wehen we access unvisited node it does
         * not throw null
         */
        Arrays.fill(vis, false);
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        /*
         * Important to create arraylist of arraylist or there would be errors
         */
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (mat.get(i).get(j) == 1 && i != j) {
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }
        for (int i = 0; i < v; i++) {
            /*
             * Below if block will be executed whenever we have completely traversed 1
             * disconnected component of the graph
             * 
             * 1st time this block will be helpful in initialising deque with first node
             */
            if (vis[i] == false) {
                vis[i] = true;
                countOfProvinces++;
                deque.addLast(i);
            }
            /*
             * Normal BFS
             */
            while (!deque.isEmpty()) {
                Integer currNode = deque.removeFirst();
                for (Integer itr : adj.get(currNode)) {
                    if (vis[itr] == false) {
                        vis[itr] = true;
                        deque.addLast(itr);
                    }
                }
            }
        }

        return countOfProvinces;

    }
}

class NumberOfProvincesDFS {
    static int numProvinces(ArrayList<ArrayList<Integer>> mat, int v) {
        // code here
        int countOfProvinces = 0;
        // when we initialise array whole array has all values as automatically 0
        // preventing one more iteration of the array using Arrays.fill for Boolean
        int vis[] = new int[v];
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        /*
         * Important to create arraylist of arraylist or there would be errors
         */
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (mat.get(i).get(j) == 1 && i != j) {
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }

        for (int i = 0; i < v; i++) {

            if (vis[i] == 0) {
                countOfProvinces++;
            }

            dfs(adj, vis, i);
        }

        return countOfProvinces;
    }

    static void dfs(ArrayList<ArrayList<Integer>> adj, int[] vis, Integer node) {
        vis[node] = 1;
        for (Integer itr : adj.get(node)) {
            if (vis[itr] == 0) {
                dfs(adj, vis, itr);
            }
        }
    }
}