
//https://www.youtube.com/watch?v=muncqlKJrH0&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=11
//https://practice.geeksforgeeks.org/problems/find-the-number-of-islands/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=find_the_number_of_islands

// Just keep calling DFS or BFS on each index of matrix and count how many times you called it

import java.util.*;

class NumberOfIslands {
    // Function to find the number of islands.
    public int numIslands(char[][] grid) {
        // Code here
        int n = grid.length;
        int m = grid[0].length;
        Boolean[][] vis = new Boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                vis[i][j] = false;
            }
        }
        Deque<Pair> deque = new ArrayDeque<>();
        int numOfIslands = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1' && vis[i][j] == false) {
                    deque.addFirst(new Pair(i, j));
                    vis[i][j] = true;
                    numOfIslands++;
                    bfs(grid, vis, deque);
                }
            }
        }

        return numOfIslands;

    }

    public void bfs(char[][] grid, Boolean[][] vis, Deque<Pair> deque) {
        while (!deque.isEmpty()) {
            Pair currNode = deque.removeLast();
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int currRow = currNode.row + i;
                    int currCol = currNode.column + j;
                    if (currRow >= 0 && currRow < grid.length && currCol >= 0 && currCol < grid[currRow].length &&
                            grid[currRow][currCol] == '1' && vis[currRow][currCol] == false) {
                        vis[currRow][currCol] = true;
                        deque.addFirst(new Pair(currRow, currCol));
                    }
                }
            }
        }
    }

    class Pair {
        int row;
        int column;

        Pair(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}