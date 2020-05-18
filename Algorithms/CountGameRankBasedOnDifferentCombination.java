// dp, runtime O(n^2) space n^2 ???

https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=628655&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D108%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline


public class findTotalCountPaiMing {
    private static int findCount(int n) {
        if (n == 0) {
            return 0;
        }
 
        int[][] dp = new int[n + 1][n + 1];
        dp[1][1] = 1;
        // ith person, j teams
        for (int i = 2; i <= n; i++) {
            for (int j = i - 1; j >= 1; j--) {
                dp[i][j + 1] += (j + 1) * dp[i - 1][j]; // (j + 1) possible team to be chosen
                dp[i][j] += j * dp[i - 1][j];
            }
        }
 
        int res = 0;
        for (int i = 1; i <= n; i++) {
            res += dp[n][i];
        }
        return res;
    }
}
