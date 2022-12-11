import java.util.*;

class NumberOfProvincesDFS {
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