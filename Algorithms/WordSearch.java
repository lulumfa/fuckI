// my latest one, O(mn* 4^k)

public class Solution {
    private static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length ==0 || board[0].length == 0 || word == null || word.length() == 0) return false;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == word.charAt(0) && dfsSearchWord(board, word, 0, i, j)) return true;
            }
        }
        
        return false;
    }
    
    private boolean dfsSearchWord(char[][] board, String word, int k, int x, int y) {
        if(k == word.length() -1) return true;
        board[x][y] ^= 512;
        for(int[] dir : dirs) {
            int i = x + dir[0];
            int j = y + dir[1];
            if(i >= 0 && i < board.length && j >=0 && j < board[0].length && board[i][j] == word.charAt(k + 1)) {
                if(dfsSearchWord(board, word, k + 1, i, j)) return true;
            }
        }
        
        board[x][y] ^= 512;
        return false;
    }
}

// my own
public class Solution {
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length ==0 || board[0].length ==0 || word == null || word.length() ==0) return false;
        
        for(int i = 0; i< board.length; i++){
            for(int j = 0; j< board[0].length; j++){
                if(dfs(board, i, j, word, 0)) return true;
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, int i, int j, String word, int k) {
        if(board[i][j] == word.charAt(k)){
            if(k == (word.length()-1)) return true;
            board[i][j] = '0';
            
            if(
                (i>0 && board[i-1][j] != '0' && dfs(board, i-1, j, word, k+1)) ||
                ((i+1)< board.length && board[i+1][j] != '0' && dfs(board, i+1, j, word, k+1)) ||
                (j>0 && board[i][j-1] != '0' && dfs(board, i, j -1, word, k+1)) ||
                ((j+1)< board[0].length && board[i][j+1] != '0' && dfs(board, i, j+1, word, k+1))
            ) {
                return true;
            }

            board[i][j] = word.charAt(k);
        } 
        return false;
    }
}

// word search II ,http://www.programcreek.com/2014/06/leetcode-word-search-ii-java/

//reference: http://blog.csdn.net/linhuanmars/article/details/24336987
//加上我们对每个顶点都要做一次搜索，所以总的时间复杂度最坏是O(m^2*n^2)，空间上就是要用一个数组来记录访问情况，所以是O(m*n)。
public class Solution {
    public boolean exist(char[][] board, String word) {
        if(board==null|| board.length==0 || board[0].length ==0) return false;
        if(word==null || word.length()==0) return true;
        
        boolean[][] visited = new boolean[board.length][board[0].length];
        
        for(int i = 0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                if(searchWord(word, 0, visited, board, i, j)) return true;
            }
        }
        return false;
    }
    
    private boolean searchWord(String s, int index, boolean[][] visited, char[][] board, int i, int j) {
        if(index==s.length()) return true;
        
        if(i<0 || j<0 || i>=board.length || j>=board[0].length || visited[i][j]|| board[i][j] != s.charAt(index)) return false;
        
        visited[i][j] = true;
        boolean res = searchWord(s, index+1, visited, board, i-1, j) ||
                      searchWord(s, index+1, visited, board, i+1, j) ||
                      searchWord(s, index+1, visited, board, i, j-1) ||
                      searchWord(s, index+1, visited, board, i, j+1);
                      
        visited[i][j] = false;
        return res;
    }
}
