
// O(n^2), same as space

/*
        Regular Expression - Dynamic Programming
        AirBnB Interview Question
     */
    public class Solution {
        public boolean regMatch(String s, String p) {
            if (s == null || p == null) return false;
            boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
            dp[0][0] = true;
            for (int i = 1; i <= p.length(); i++) {
                if (p.charAt(i - 1) == '*' && dp[i - 2][0]) dp[i][0] = true;
            }

            for (int i = 1; i <= p.length(); i++) {
                for (int j = 1; j <= s.length(); j++) {
                    if (p.charAt(i - 1) == '.' || p.charAt(i - 1) == s.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (p.charAt(i - 1) == '*' || p.charAt(i - 1) == '+') {
                        if (p.charAt(i - 2) == '.' || p.charAt(i - 2) == s.charAt(j - 1)) {
                            if (p.charAt(i - 1) == '*') {
                                dp[i][j] = dp[i - 2][j] || dp[i - 2][j - 1] || dp[i][j - 1];
                            } else {
                                dp[i][j] = dp[i - 2][j - 1] || dp[i][j - 1];
                            }
                        } else {
                            dp[i][j] = p.charAt(i - 1) == '*' && dp[i - 2][j];
                        }
                    }
                }
            }
            return dp[p.length()][s.length()];
        }
    }

    public static class UnitTest {

        @Test
        public void test1() {
            Solution sol = new RegularExpression().new Solution();
            assertTrue(sol.regMatch("saaaa", "s+a*"));
            assertFalse(sol.regMatch("saaaa", "s+b*"));
            assertFalse(sol.regMatch("saaaab", "s+a*"));
            assertFalse(sol.regMatch("saaaab", "s+b*"));
        }
    }
}
