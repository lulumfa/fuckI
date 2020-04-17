// O(mn), space stack which is O(mn) in worst case

class Solution {
    private final static int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || image[0].length == 0 || sr < 0 ||
           sr >= image.length || sc < 0 || sc >= image[0].length) return image;
        
        if (image[sr][sc] != newColor) dfsFill(image, sr, sc, image[sr][sc], newColor);
        return image;
    }
    
    private void dfsFill(int[][] image, int i, int j, int oldColor, int newColor) {
        image[i][j] = newColor;
        
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < image.length && y >= 0 && y < image[0].length && image[x][y] == oldColor) dfsFill(image, x, y, oldColor, newColor);
        }
    }
}
