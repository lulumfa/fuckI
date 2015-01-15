//reference: http://blog.csdn.net/linhuanmars/article/details/22126357
// permutation, considering the n! could be very large, so using long instead of int to store the res
public class Solution {
    public int uniquePaths(int m, int n) {
        if(m<=0 || n<=0) return 0;
        int large = Math.max(m-1, n-1);
        int small = Math.min(m-1, n-1);
        long mult1 =1, mult2 = 1;
        for(int i = 1; i <=small; i++) {
            mult1*= i;
            mult2*= large+small-i+1;
        }
        return (int)(mult2/mult1);
    }
}

// NP
public class Solution {
    public int uniquePaths(int m, int n) {
        if(m<=0 || n<=0) return 0;
        int[] res = new int[n];
        for(int i = 0; i<n; i++) {
            res[i] = 1;
        }
        for(int i = 1; i<m; i++) {
            for(int j =1; j<n; j++) {
                res[j] = res[j] + res[j-1];
            }
        }
        return res[n-1];
    }
}
