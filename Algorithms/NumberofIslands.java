public class Solution {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length ==0 || grid[0].length ==0) return 0;
        int res=  0;
        for(int i =0; i< grid.length; i++){
            for(int j = 0; j< grid[0].length; j++) {
                if(grid[i][j] == '1'){
                    res++;
                    bfsFillZero(grid, i, j);
                }
            }
        }
        return res;
    }
    
    private void bfsFillZero(char[][] board, int i, int j){
        board[i][j] = '0';
        if(i>0 && board[i-1][j] == '1') bfsFillZero(board, i-1, j);
        if((i+1) < board.length && board[i+1][j] == '1') bfsFillZero(board, i+1, j);
        if(j>0 && board[i][j-1] == '1') bfsFillZero(board, i, j-1);
        if((j+1) < board[0].length && board[i][j+1] == '1') bfsFillZero(board, i, j+1);
    }
}
