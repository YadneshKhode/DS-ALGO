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
}
