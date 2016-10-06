//O(n2) space O(n2)

public class Solution {
    
    public static final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; 
    
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        
        int res = 1;
        int[][] dp = new int[matrix.length][matrix[0].length];
        
        for(int i = 0; i< matrix.length; i++) {
            for(int j = 0; j< matrix[0].length; j++) {
                res = Math.max(res, dfs(matrix, dp, i, j));
            }
        }
        return res;
    }
    
    private int dfs(int[][] matrix, int[][] dp, int i, int j) {
        if(dp[i][j] > 0) return dp[i][j];
        int res = 0;
        for(int[] dir: Solution.dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if(x >=0 && x < matrix.length && y >=0 && y < matrix[0].length) {
                if(matrix[x][y] > matrix[i][j]) {
                    res = Math.max(res, dp[x][y] > 0 ? dp[x][y] : dfs(matrix, dp, x, y));
                }
            }
        }
        res++;
        dp[i][j] = res;
        return res;
    }
}
