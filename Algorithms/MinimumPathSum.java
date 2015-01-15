//reference: http://blog.csdn.net/linhuanmars/article/details/22257673

public class Solution {
    public int minPathSum(int[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0) return 0;
        
        int[] res = new int[grid[0].length];
        
        res[0] = grid[0][0];
        for(int i=1; i<grid[0].length; i++) {
            res[i] = res[i-1] + grid[0][i];
        }
        
        for(int i = 1; i<grid.length; i++) {
            res[0] +=grid[i][0];
            for(int j = 1; j<grid[0].length; j++) {
                res[j] = grid[i][j] + Math.min(res[j], res[j-1]);
            }
        }
        return res[res.length-1];
    }
}
