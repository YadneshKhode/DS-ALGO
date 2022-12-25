
//https://practice.geeksforgeeks.org/problems/distance-of-nearest-cell-having-1-1587115620/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=distance-of-nearest-cell-having-1

//https://takeuforward.org/graph/distance-of-nearest-cell-having-1/

import java.util.*;

public class DistanceOfNearestCellHaving1 {
    // Function to find distance of nearest 1 in the grid for each cell.
    public int[][] nearest(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] res = new int[n][m];
        Deque<Pair> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    // marking all 1's as 0 since dist from 1 to nearest 1 is 0
                    res[i][j] = 0;
                    // adding co-ordinates of 1's in the deque so we can start our BFS from these
                    // points
                    deque.addLast(new Pair(i, j));
                }
                /*
                 * marking other indices as MAX_VALUE because when we try to find the value of
                 * that index we can store min of either that index's current value or replace
                 * it with something less
                 */
                else
                    res[i][j] = Integer.MAX_VALUE;
            }
        }

        int delRow[] = { -1, 0, 1, 0 };
        int delCol[] = { 0, 1, 0, -1 };

        while (!deque.isEmpty()) {
            Pair currNode = deque.removeFirst();

            for (int i = 0; i < 4; i++) {
                int newRow = delRow[i] + currNode.rowIndex;
                int newCol = delCol[i] + currNode.colIndex;
                // making sure it does not go out of bound and we update the index only when it
                // is less than its current value
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m
                        && res[currNode.rowIndex][currNode.colIndex] + 1 < res[newRow][newCol]) {
                    res[newRow][newCol] = res[currNode.rowIndex][currNode.colIndex] + 1;
                    /*
                     * We need to add in the deque because of following reasons - even i have not
                     * understood 100% - Now I have idea see the rotten oranges problem or the below
                     * explanation of Approach-2
                     * 1. We visit the index with value 1 in the grid array then we visit the
                     * neighbours so now using this neighbours we have to visit futher indices in
                     * the 2D
                     * 
                     * 2. for e.g. we reach index (2,3) from index (2,2) and store its value as 3
                     * since value at (2,2) was for e.g. 2 so 2+1 is 3 -> it means the cost to reach
                     * until (2,2) was 2 and then from (2,2) to (2,3) is 1 units, total = 2+1 = 3
                     * 
                     * 3. We keep doing it to until deque becomes array at this juncture all indices
                     * has smallest value possible
                     */
                    deque.addLast(new Pair(newRow, newCol));
                }
            }

        }
        return res;
    }

    private class Pair {
        int rowIndex;
        int colIndex;

        public Pair(int rowIndex, int colIndex) {
            this.rowIndex = rowIndex;
            this.colIndex = colIndex;
        }
    }
}

/******************
 * APPROACH-2-BETTER-APPROACH-JUST-LIKE-ROTTEN-ORANGES
 *************************/

class Solution {
    // Function to find distance of nearest 1 in the grid for each cell.
    public int[][] nearest(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] res = new int[n][m];
        Deque<Pair> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    /*
                     * Since distance from 1 to closest 1 is 0.
                     * Add all 1 to the deque, why? I will ans below
                     */
                    res[i][j] = 0;
                    deque.addLast(new Pair(i, j));
                }
                /*
                 * Need something to differentiate that we have not traversed this cell yet.
                 */
                else
                    res[i][j] = Integer.MAX_VALUE;
            }
        }

        /*
         * So we can efficiently traverse up down left and right
         */
        int delRow[] = { -1, 0, 1, 0 };
        int delCol[] = { 0, 1, 0, -1 };

        while (!deque.isEmpty()) {
            /*
             * 1. The idea is to "simultaneously" traverse 1 unit distance in all 4
             * directions of all the "1"s in the 2D matrix. (1 unit distance means
             * traversing to the immediate neighbouring up/below/left/right cell from
             * current cell)
             * 
             * 2. By doing so when we "visit" any cell for the 1st time we ensure the value
             * that we are placing there is guaranteed to be minimum distance from nearest 1
             * 
             * 3. Let's understand how this works, initially we add all the indices of 1s
             * that we encounter while traversing the 2D matrix to the deque and mark the
             * cells of this 1s as 0 since the closest 1 for this cell is itself and hence
             * the distance is 0, and we mark other cells with value 0 as Integer.MAX_VALUE
             * so we can differentiate our distance 0 with the default values of "res" 2D
             * array's 0.
             * 
             * 4. Now take this e.g. where in the initial matrix in question has 3 1s in
             * whole 2D array and rest of them are 0s, after performing above step we will
             * have 3 elements in deque each with index of the 1s.
             * 
             * 5. Now we pop first 1s index from deque and traverse 1 unit distance in each
             * direction and mark the cells with value of current cell (which is 0 as we had
             * set it earlier) + 1(since we had to move 1 unit distance to reach this new
             * cell) and store this value in that cell and now this cell has been marked
             * visited (since its value is no longer Integer.MAX_VALUE).
             * 
             * 6. Do same this for other 2 elements in the deque and we will visit only
             * those cells that are not visited, because the visited cells value will always
             * be 1 since for other 2 elements as well this visited cell will be at distance
             * 1 and even if they were allowed to visit they will make the value as 0 + 1 =
             * 1, but the cells value is already 1 so why bother updating it again
             * 
             * 7. Now, when we removed the first element of the deque we have to add the
             * cells that were updated, why? See, in normal Graphs we have an adjacency list
             * and when we visit current node we add the elements in its adjacency list to
             * the deque becayse only then we can move further to the other unvisited parts
             * of the graphs.
             * 
             * 8.now similarly we do for the other 2 elements and now we have adjacent
             * elements of all the 1s in the deque and all these elements will
             * "simultaneously" visit their neighbouring cells and time taken will be of 2
             * units, hence for all the visited cells no matter from which 1 you are
             * visiting the value will be exactly same as any other 1
             */
            Pair currNode = deque.removeFirst();

            for (int i = 0; i < 4; i++) {
                int newRow = delRow[i] + currNode.rowIndex;
                int newCol = delCol[i] + currNode.colIndex;

                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m
                        && res[newRow][newCol] == Integer.MAX_VALUE) {
                    res[newRow][newCol] = res[currNode.rowIndex][currNode.colIndex] + 1;
                    deque.addLast(new Pair(newRow, newCol));
                }
            }

        }
        return res;
    }

    private class Pair {
        int rowIndex;
        int colIndex;

        public Pair(int rowIndex, int colIndex) {
            this.rowIndex = rowIndex;
            this.colIndex = colIndex;
        }
    }

}