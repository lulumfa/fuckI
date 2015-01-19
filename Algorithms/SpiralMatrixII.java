//reference: http://blog.csdn.net/linhuanmars/article/details/21906331

//时间复杂度是O(n^2)

public class Solution {
    public int[][] generateMatrix(int n) {
        if(n<0) return null;
        int[][] res = new int[n][n];
        int count = 1;
        int round = n/2;
        for(int i = 0; i<round; i++) {
            for(int j = i; j<n-i-1; j++) {
                res[i][j] = count++;
            }
            for(int j = i; j<n-i-1; j++) {
                res[j][n-i-1] = count++;
            }
            for(int j = n-1-i; j>i; j--) {
                res[n-i-1][j] = count++;
            }
            for(int j = n-1-i; j>i; j--) {
                res[j][i] = count++;
            }
        }
        
        if(n%2==1) {
            res[round][round] = count;
        }
        return res;
    }
}
