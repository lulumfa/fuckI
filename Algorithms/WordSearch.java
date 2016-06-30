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
