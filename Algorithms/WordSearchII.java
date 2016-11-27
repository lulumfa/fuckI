// O(mn * 3^longestK + l*k) k = length of word, building trie = O(l*k), l = words.length

public class Solution {
    private static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<String>();
        if(board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) return res;
        TrieNode root = buildTrie(words);
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                dfsFindWords(board, i, j, res, root);
            }
        }
        return res;
    }
    
    private void dfsFindWords(char[][] board, int i, int j, List<String> res, TrieNode node) {
        char c = board[i][j];
        if(c == '\u0000' || node.children[c -'a'] == null) return;
        node = node.children[c -'a'];
        if(node.word != null) {
            res.add(node.word);
            node.word = null;
        }
        board[i][j] = '\u0000';        
        for(int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if(x >=0 && x < board.length && y >=0 && y < board[0].length) {
                dfsFindWords(board, x, y, res, node);
            }
        }
        board[i][j] = c;
    }
    
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for(String word : words) {
            TrieNode p = root;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if(p.children[c - 'a'] == null) p.children[c - 'a'] = new TrieNode();
                p = p.children[c - 'a'];
            }
            p.word = word;
        }
        
        return root;
    }
}

class TrieNode {
    String word;
    TrieNode[] children;
    
    public TrieNode() {
        this.children = new TrieNode[26];
    }
}
