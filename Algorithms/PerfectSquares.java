// O(n*sqrt(n)) = O(n1.5) space O(n)

public class Solution {
    public int numSquares(int n) {
        if(n < 0) return 0;
        int[] dp = new int[n+1];
        
        for(int i = 0; i<=n; i++) {
            dp[i] = i;
            for(int j = 1; j*j <= i; j++) {
                dp[i] = Math.min(dp[i], 1 + dp[i - j*j]);
            }
        }
        
        return dp[n];
    }
}
