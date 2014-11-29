// dp 

// reference: http://blog.csdn.net/worldwindjp/article/details/22066307

public class Solution {
    public int minCut(String s) {
        if(s==null || s.length()<=1) return 0;
        int[] dpMinCut = new int[s.length()+1];
        int[][] dpPalindrome = new int[s.length()][s.length()];
        for(int i = s.length()-1; i>=0; i--) {
            dpMinCut[i] = s.length()-i;
            for(int j = i; j< s.length(); j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    if(j-i<=2 || dpPalindrome[i+1][j-1] ==1 ){
                        dpPalindrome[i][j] = 1;
                        dpMinCut[i] = Math.min(dpMinCut[i], dpMinCut[j+1] + 1);
                    }
                }
            }
        }
        return dpMinCut[0]-1;
    }
}


// http://www.cnblogs.com/feiling/p/3245919.html

public class Solution {
    public static int minCut(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }

        int len = s.length();
        int[] dp = new int[len];
        boolean[][] palindromeMatrix = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            palindromeMatrix[i][i] = true;
        }

        // DP to check every substring of s is palindrome or not?
        // substring length start from 2 to n
        for (int L = 2; L <= len; L++) {
            for (int i = 0; i < len - L + 1; i++) {
                int j = i + L - 1;
                if (L == 2) {
                    palindromeMatrix[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    palindromeMatrix[i][j] = (s.charAt(i) == s.charAt(j) && palindromeMatrix[i + 1][j - 1]);
                }
            }
        }

        for (int i = len - 1; i >= 0; i--) {
            if(palindromeMatrix[i][len - 1]){
                dp[i] = 0;
            } else {
                int min = Integer.MAX_VALUE;
                for (int k = i + 1; k < len; k++) {
                    if(palindromeMatrix[i][k - 1])
                        min = Math.min(min, 1 + dp[k]);
                }
                dp[i] = min;
            }
        }
        return dp[0];
    }
}
