//reference: http://blog.csdn.net/linhuanmars/article/details/20667175

public class Solution {
    public List<String[]> solveNQueens(int n) {
        List<String[]> res = new ArrayList<String[]>();
        int[] qCol = new int[n];
        helper(n, 0, qCol, res);
        return res;
    }
    
    private void helper(int n, int row, int[] qCol, List<String[]> res) {
        if(row==n) {
            String[] board = new String[n];
            for(int i = 0; i<n; i++) {
                StringBuilder builder = new StringBuilder();
                for(int j=0; j<n; j++) {
                    if(qCol[i] == j) builder.append("Q");
                    else builder.append(".");
                }
                board[i] = builder.toString();
            }
            res.add(board);
            return;    
        }
        for(int i=0; i<n; i++) {
            qCol[row] = i; 
            if(check(row, qCol)) helper(n, row+1, qCol, res);
        }
    }
    
    private boolean check(int row,int[] qCol) {
        for(int i=0; i<row; i++) {
            if(qCol[i] == qCol[row] || (Math.abs(qCol[i]- qCol[row]) == (row-i))) return false;
        }
        return true;
    }
}
