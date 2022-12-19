//https://takeuforward.org/graph/flood-fill-algorithm-graphs/
public class FloodfillAlgorithm {
    public int[][] floodFillRecursiveMethod(int[][] image, int sr, int sc, int newColor) {
        boolean[][] vis = new boolean[image.length][image[0].length];

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                vis[i][j] = false;
            }
        }

        floodFillRecur(image, sr, sc, newColor, vis, image[sr][sc]);

        return image;
    }

    public void floodFillRecur(int[][] image, int sr, int sc, int newColor, boolean[][] vis, int startPixel) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || vis[sr][sc] == true
                || image[sr][sc] != startPixel) {
            return;
        }
        vis[sr][sc] = true;
        image[sr][sc] = newColor;
        floodFillRecur(image, sr - 1, sc, newColor, vis, startPixel);
        floodFillRecur(image, sr + 1, sc, newColor, vis, startPixel);
        floodFillRecur(image, sr, sc + 1, newColor, vis, startPixel);
        floodFillRecur(image, sr, sc - 1, newColor, vis, startPixel);
    }

    public int[][] floodFillDFS(int[][] image, int sr, int sc, int newColor) {
        int delRow[] = { -1, 0, +1, 0 };
        int delCol[] = { 0, +1, 0, -1 };
        dfs(image, sr, sc, newColor, image[sr][sc], delRow, delCol);

        return image;
    }

    /*
     * Time Complexity: O(NxM + NxMx4) ~ O(N x M)
     * 
     * For the worst case, all of the pixels will have the same colour, so DFS
     * function will be called for (N x M) nodes and for every node we are
     * traversing for 4 neighbours, so it will take O(N x M x 4) time.
     * 
     * 
     * Space Complexity: O(N x M) + O(N x M)
     * 
     * O(N x M) for copied input array(we edited the input which is bad practice
     * ideally we should have created a copy of images array and returned that as
     * answer) and recursive stack space takes up N x M
     * locations at max.
     */
    public void dfs(int[][] image, int sr, int sc, int newColor, int startPixel, int delRow[], int delCol[]) {
        image[sr][sc] = newColor;
        for (int i = 0; i < 4; i++) {
            int nrow = sr + delRow[i];
            int ncol = sc + delCol[i];
            if (nrow >= 0 && nrow < image.length && ncol >= 0 && ncol < image[0].length
                    && image[nrow][ncol] == startPixel && image[nrow][ncol] != newColor) {
                dfs(image, nrow, ncol, newColor, startPixel, delRow, delCol);
            }
        }

    }
}
