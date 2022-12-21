
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
                     * understood 100%
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
