//reference: http://blog.csdn.net/linhuanmars/article/details/20668017

public class Solution {
    public int totalNQueens(int n) {
        int[] res = {0};
        helper(n, 0, new int[n], res);
        return res[0];
    }
    
    private void helper(int n, int row, int[] qCol, int[] res) {
        if(row==n) {
            res[0] = res[0] +1;
        } else {
            for(int i=0; i<n;i++) {
                qCol[row] = i;
                if(check(row, qCol)) helper(n, row+1, qCol, res);
            }
        }
        
    }
    
    private boolean check(int row, int[] qCol) {
        for(int i=0; i<row; i++) {
            if(qCol[i] == qCol[row] || (Math.abs(qCol[i]-qCol[row]) == row -i)) return false;
        }
        return true;
    }
}
