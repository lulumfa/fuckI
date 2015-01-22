//http://blog.csdn.net/linhuanmars/article/details/21503683

// 时间复杂度是O(矩阵的大小)，空间复杂度是O(1)

public class Solution {
    public void rotate(int[][] matrix) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0) return;
        int n = matrix.length;
        int layer = n/2;
        
        for(int i = 0; i<layer; i++) {
            for(int j = i; j<n-i-1; j++) {
                int temp = matrix[n-j-1][i];
                matrix[n-j-1][i] = matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
                matrix[j][n-i-1] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }
}
