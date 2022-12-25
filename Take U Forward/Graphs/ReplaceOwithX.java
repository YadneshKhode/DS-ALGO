
//https://practice.geeksforgeeks.org/problems/replace-os-with-xs0052/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=replace-os-with-xs
//https://www.youtube.com/watch?v=BtdgAys4yMk&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=16
//https://takeuforward.org/graph/surrounded-regions-replace-os-with-xs/
import java.util.*;

/*
 * 1. Traverse boundary, mark all 'O' as visited and add their index to the deque
 * 2. call BFS or DFS until deque is empty and keep marking 'O' as visited.
 * 3. Once deque is empty iterate over visited array and check for unvisited 'O' get their index and mark them as 'X' in the input 2D array 
 */

public class ReplaceOwithX {
    static char[][] fill(int n, int m, char a[][]) {
        // code here
        int[][] vis = new int[n][m];
        int[] del = { 0, m - 1 };
        Deque<Pair> deque = new ArrayDeque<>();

        // traversing 1st column and last
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                if (a[i][del[j]] == 'O') {
                    vis[i][del[j]] = 1;
                    deque.addLast(new Pair(i, del[j]));
                }
            }
        }

        // Use either above version while coding or below version whichever seems
        // simpler

        // traversing first row
        for (int i = 0; i < m; i++) {
            if (a[0][i] == 'O') {
                vis[0][i] = 1;
                deque.addLast(new Pair(0, i));
            }
        }
        // traversing last row
        for (int i = 0; i < m; i++) {
            if (a[n - 1][i] == 'O') {
                vis[n - 1][i] = 1;
                deque.addLast(new Pair(n - 1, i));
            }
        }

        int delRow[] = { -1, 0, 1, 0 };
        int delCol[] = { 0, 1, 0, -1 };

        while (!deque.isEmpty()) {
            Pair currNode = deque.removeFirst();
            for (int i = 0; i < 4; i++) {
                int newRow = currNode.rowIndex + delRow[i];
                int newCol = currNode.colIndex + delCol[i];
                if (newRow >= 0 && newCol >= 0 && newRow < n && newCol < m && vis[newRow][newCol] == 0
                        && a[newRow][newCol] == 'O') {
                    vis[newRow][newCol] = 1;
                    deque.addLast(new Pair(newRow, newCol));
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (vis[i][j] == 0) {
                    a[i][j] = 'X';
                }
            }
        }

        return a;

    }

    private static class Pair {
        int rowIndex;
        int colIndex;

        public Pair(int rowIndex, int colIndex) {
            this.rowIndex = rowIndex;
            this.colIndex = colIndex;
        }
    }
}
