//reference: http://blog.csdn.net/linhuanmars/article/details/22126357
// permutation


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
