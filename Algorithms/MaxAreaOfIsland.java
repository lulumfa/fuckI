// O(n), space O(n) for size of stackM

class Solution {
   private int getArea(int[][] grid, int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] == 0) return 0;

        grid[row][col] = 0;

        return 1 + getArea(grid, row-1, col)
            + getArea(grid, row+1, col)
            + getArea(grid, row, col-1)
            + getArea(grid, row, col+1);
    }
    
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int maxArea = 0;

        for (int row=0; row < grid.length; row++) {
            for (int col=0; col < grid[0].length; col++) {
                if (grid[row][ col] == 1) {
                     int area = getArea(grid, row, col);
                     maxArea = Math.max(area, maxArea);
                }
            }
        }

        return maxArea;
    }
}
