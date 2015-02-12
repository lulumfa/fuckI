//reference: http://blog.csdn.net/linhuanmars/article/details/22904855

//所以每个结点改变次数不会超过一次，因而是O(m*n)的复杂度，最后一次遍历同样是O(m*n)，所以总的时间复杂度是O(m*n)。
//空间上就是递归栈（深度优先搜索）或者是队列（广度优先搜索）的空间，同时存在的空间占用不会超过O(m+n)（以广度优先搜索为例，
//每次队列中的结点虽然会往四个方向拓展，但是事实上这些结点会有很多重复，假设从中点出发，可以想象最大的扩展不会超过一个菱形，
//也就是n/2*2+m/2*2=m+n，所以算法的空间复杂度是O(m+n)

public class Solution {
    public void solve(char[][] board) {
        if(board==null || board.length==0 || board[0].length==0) return;
        
        for(int i = 0; i<board.length; i++) {
            fill(board, i, 0);
            fill(board, i, board[0].length-1);
        }
        
        for(int i = 0; i<board[0].length; i++) {
            fill(board, 0, i);
            fill(board, board.length-1, i);
        }
        
        for(int i = 0; i<board.length; i++) {
            for(int j = 0; j<board[0].length; j++) {
                if(board[i][j]=='O') {
                    board[i][j] = 'X';
                } else if(board[i][j] =='Y') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    private void fill(char[][] board, int i, int j) {
        if(board[i][j]!='O') return;
        board[i][j] = 'Y';
        Queue<Integer> queue = new LinkedList<Integer>();
        int temp = i*board[0].length+j;
        queue.offer(temp);
        
        while(!queue.isEmpty()){
            temp = queue.poll();
            int row = temp/board[0].length;
            int col = temp%board[0].length;
            
            if(row>0 && board[row-1][col]=='O') {
                board[row-1][col]='Y';
                queue.offer((row-1)*board[0].length+col);
            }
            if(row<board.length-1 && board[row+1][col]=='O') {
                board[row+1][col]='Y';
                queue.offer((row+1)*board[0].length+col);
            }
            if(col>0 && board[row][col-1]=='O') {
                board[row][col-1]='Y';
                queue.offer(row*board[0].length+col-1);
            }
            if(col<board[0].length-1 && board[row][col+1]=='O') {
                board[row][col+1]='Y';
                queue.offer(row*board[0].length+col+1);
            }
        }
        
    }
}
