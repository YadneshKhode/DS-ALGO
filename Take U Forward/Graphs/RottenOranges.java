
//https://takeuforward.org/data-structure/rotten-oranges/
//https://practice.geeksforgeeks.org/problems/rotten-oranges2536/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=rotten_oranges 

import java.util.*;

class RottenOranges {
    // Function to find minimum time required to rot all oranges.
    public int orangesRotting(int[][] grid) {
        Deque<Pair> deque = new ArrayDeque<>();
        int n = grid.length, m = grid[0].length;
        int[][] vis = new int[n][m];
        int cntFresh = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // if cell contains rotten orange
                if (grid[i][j] == 2) {
                    // the step of adding all rotten oranges to the deque is necessary because these
                    // oranges will be scattered across the grid and all these oranges will rotten
                    // their neighbours with distance unit 1 in 1 unit time, so imagine my current
                    // rotten orange is on top right corner and in unit 1 time it was able to rot
                    // its neighbours but at the same time some other orange far away on bottom left
                    // of the grid will ALSO rotten its neighbors at that same instant of time,
                    // hence when we add 1st all rotten oranges of the grid to the deque it works
                    // fine.
                    deque.addLast(new Pair(i, j, 0));
                    // mark as visited (rotten) in visited array
                    vis[i][j] = 2;
                }
                // count fresh oranges
                if (grid[i][j] == 1)
                    cntFresh++;
            }
        }

        // delta row and delta column -> it moves anti clock wise starting from below->
        // right -> top -> left (assume you are in middle then imagine)
        int drow[] = { -1, 0, +1, 0 }; // {below row, same row, above row, same row}
        int dcol[] = { 0, 1, 0, -1 }; // {same column, right column, same column, left column}
        int overallRotMax = -1;

        while (!deque.isEmpty()) {
            Pair currNode = deque.removeFirst();
            int newRot = currNode.rotUnit;
            /*
             * 
             * LOGIC - One confusing part of the logic states that We have to visit each
             * cell only once and still in the end we will have the optimal minimum time
             * taken to rot that particular cell, for each cell we "dont" have to find the
             * rotting path from all rotten oranges to this particular cell and get the
             * minimum of all the paths.
             * 
             * 1. The logic works because initially we add all rotten oranges in the deque
             * and when we pop first orange we travel exact 1 unit distance in all 4
             * directions and if these 4 directions are valid and not visited then we add
             * them to the "back" of the queue.
             * 
             * 2. Now next element is the 2nd rotten orange that we added initially and we
             * repeat same above steps
             * 
             * 3. Assume in the initial state there were only 3 rotten oranges, we added 3
             * rotten oranges in the deque and now we have travelled exactly 1 unit distance
             * from all these rotten oranges now even if there was overlap of cells between
             * the neighbours of these 3 rotten oranges it doesnt matter who is the source
             * rotten orange for this particular cell since the unit time taken will always
             * be 1 no matter the source
             * 
             * 4. Now all initially added oranges have been removed and their respective
             * neighbours have been added to the deque again we will travel only 1 unit
             * distance from these oranges and mark neighbouring oranges as rotten
             * now for 1 particular cell its time taken to rot will always be 2 no matter
             * from whatever starting point(starting point means the position of initial
             * rotten oranges) you count the time taken to rot this orange will always
             * be 2 and hence it is OK to visit each cell only once and still get the
             * minimum unit time taken to rot the orange because we are moving 1 unit
             * distance "simultaneously" from all rotten oranges just like real world.
             */
            for (int i = 0; i < 4; i++) {
                int newRow = currNode.rowIndex + drow[i];
                int newCol = currNode.colIndex + dcol[i];

                if (newRow >= 0 && newCol >= 0 && newRow < n && newCol < m && vis[newRow][newCol] == 0
                        && grid[newRow][newCol] == 1) {
                    cntFresh--;
                    vis[newRow][newCol] = 2;
                    overallRotMax = Math.max(overallRotMax, newRot + 1);
                    deque.addLast(new Pair(newRow, newCol, newRot + 1));
                }

            }

        }

        if (cntFresh != 0)
            return -1;
        return overallRotMax;

    }

    class Pair {
        int rowIndex;
        int colIndex;
        int rotUnit;

        Pair(int rowIndex, int colIndex, int rotUnit) {
            this.rowIndex = rowIndex;
            this.colIndex = colIndex;
            this.rotUnit = rotUnit;
        }
    }
}



// Below code is latest by yadnesh on 30th May 2024


class Solution {
    public int orangesRotting(int[][] grid) {
        ArrayDeque<Pair> deque = new ArrayDeque<>();
        int countOfOne = 0, res = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1) countOfOne++;
                if(grid[i][j] == 2){
                    deque.addFirst(new Pair(i,j,0));
                } 
            }
        }

        int drow[] = { -1, 0, +1, 0 }; 
        int dcol[] = { 0, 1, 0, -1 };


        while(!deque.isEmpty()){
            Pair currNode = deque.removeFirst();
            for(int i = 0; i < 4; i++){
                int row = currNode.row + drow[i];
                int col = currNode.column + dcol[i];

            if(row >= 0 && col >= 0 && row < grid.length && col < grid[0].length && grid[row][col] == 1){
                int time = currNode.currTime;
                grid[row][col] = 2;
                countOfOne--;
                res = Math.max(res, time + 1);
                deque.addLast(new Pair(row, col, time + 1));
            }

            }
        }

        if(countOfOne > 0) return -1;

        return res;

    }
}

class Pair {
    int row;
    int column;
    int currTime;

    Pair(int a, int b, int t) {
        row = a;
        column = b;
        currTime = t;
    }
}
