// my latest one
public class Solution {
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length <1 || grid[0].length <1) return 0;
        
        int[] dp = new int[grid[0].length];
        
        for(int i=0; i< grid.length; i++){
            for(int j =0; j< grid[0].length; j++){
                if(j == 0){
                   dp[0] += grid[i][j]; 
                } else if(i ==0){
                    dp[j] = dp[j-1] + grid[i][j];
                } else {
                    dp[j] = (Math.min(dp[j], dp[j-1])  + grid[i][j]);
                }
            }
        }
        
        return dp[grid[0].length -1];
    }
}

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
