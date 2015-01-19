//reference: http://blog.csdn.net/linhuanmars/article/details/21667181

//所以时间复杂度是O(m*n)，m，n是分别是矩阵的行数和列数，空间复杂度是O(1)。

public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if(matrix==null || matrix.length==0 || matrix[0].length==0) return res;
        
        int minLen = Math.min(matrix.length, matrix[0].length);
        int round = minLen/2;
        
        for(int i = 0; i<round; i++) {
            for(int j = i; j<matrix[i].length-i-1; j++) {
                res.add(matrix[i][j]);
            }
            for(int j = i; j<matrix.length-i-1; j++) {
                res.add(matrix[j][matrix[i].length-i-1]);
            }
            for(int j = matrix[i].length-i-1; j>i; j--) {
                res.add(matrix[matrix.length-i-1][j]);
            }
            for(int j = matrix.length-i-1; j>i; j--) {
                res.add(matrix[j][i]);
            }
                
        }
        if(minLen%2==1) {
            if(minLen==matrix.length) {
                for(int j = round; j<matrix[round].length-round; j++) {
                    res.add(matrix[round][j]);
                }
            } else {
                for(int j = round; j<matrix.length-round; j++) {
                    res.add(matrix[j][matrix[round].length-round-1]);
                }
            }
        }
        return res;
    }
}
