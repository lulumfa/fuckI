// O(mn), space enhancement to O(n)

public class Solution {
    // if(matrix[i][j] == '1') dp[i][j] = 1 + Math.min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
    
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        
        int m = matrix.length, n = matrix[0].length;
        
        int[] dp = new int[n+1];
        int max = 0;
        for(int i = 0; i < m; i++) {
            int[] cur = new int[n+1];
            
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == '1') {
                    cur[j + 1] = 1 + Math.min(Math.min(dp[j], dp[j + 1]), cur[j]);
                    max = Math.max(max, cur[j+1]);
                }
            }
            
            dp = cur;
        }
        
        return max * max;
    }
}

// O(mn), space O(mn)

public class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length ==0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int maxLen = 0; 
        
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(matrix[i-1][j-1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1; 
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        return maxLen * maxLen;
    }
}
