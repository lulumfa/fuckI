// 用trie+DFS 找到所有words
// DFS，分􀡛是不取􄘉个􀦅􄇽，取第一个序列，取第二个序列
// k of dict size, len of word longest, m, n
// O(k*len + mn * k*len + 2^k*len)

import java.io.*;
import java.util.*;
import org.junit.*;
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

public class Solution {
  
public static void main(String[] args) {
    BoggleGame bg = new BoggleGame();
        char[][] board = {
                {'o', 'a', 't', 'h'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        Set<String> dict = new HashSet<>();
        dict.add("oath");
        dict.add("pea");
        dict.add("eat");
        dict.add("rain");
        dict.add("etae");
        dict.add("iflv");
        
        System.out.println(bg.fillInMostStr(board, dict));
        
       // count of the conneected path with max words 
      String[] dic={"abs","abc","dd","bb"};
      char[][] mat={{'a','b','c'},{'d','d','d'},{'b','b','d'}};
      BoggleGamePathLength sol=new BoggleGamePathLength();
      System.out.println(sol.findmaxPath(dic,mat));
      
      // path of max words, connected requred
    String[] dct = {"abcddd","abc"};
    char[][] matr = {{'a','b','c'},{'d','d','d'},{'b','b','d'}};
    BoggleGamePath s = new BoggleGamePath();
    s.findmaxPath(dct, matr);
    System.out.println(s.longest);

    String[] dct2 = {"abs","abc","dd","bb"};
    s = new BoggleGamePath();
    s.findmaxPath(dct2, matr);
    System.out.println(s.longest);
  }
}

class BoggleGame {
  
    /*
        Boggle Game
        AirBnB Interview Question
     */
        private final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        private String path2Word(char[][] board, List<int[]> curPath) {
            StringBuilder sb = new StringBuilder();
            for (int[] coor : curPath) {
                sb.append(board[coor[0]][coor[1]]);
            }
            return sb.toString();
        }

        private void search(List<List<int[]>> paths, char[][] board, int x, int y, Trie trie,
                            boolean[][] visited, List<int[]> curPath) {
            String curWord = path2Word(board, curPath);
            SearchResult result = trie.search(curWord);
            if (!result.hasPrefix) {
                return;
            }
            if (result.hasWord) {
                paths.add(new ArrayList<>(curPath));
            }

            int m = board.length;
            int n = board[0].length;

            for (int[] dir : dirs) {
                int xx = x + dir[0];
                int yy = y + dir[1];

                if (xx < 0 || xx >= m || yy < 0 || yy >= n) {
                    continue;
                }

                visited[xx][yy] = true;
                curPath.add(new int[]{xx, yy});
                search(paths, board, xx, yy, trie, visited, curPath);
                curPath.remove(curPath.size() - 1);
                visited[xx][yy] = false;
            }
        }

        private void searchWords(List<String> res, List<String> curWords, List<List<int[]>> paths,
                                 int start, boolean[][] visited, char[][] board) {
            if (start == paths.size()) {
                if (curWords.size() > res.size()) {
                    res.clear();
                    res.addAll(curWords);
                }
                return;
            }

            for (int i = start; i < paths.size(); i++) {
                boolean canUse = true;
                for (int[] coor : paths.get(i)) {
                    if (visited[coor[0]][coor[1]]) {
                        canUse = false;
                        break;
                    }
                }

                if (canUse) {
                    for (int[] coor : paths.get(i)) {
                        visited[coor[0]][coor[1]] = true;
                    }
                    curWords.add(path2Word(board, paths.get(i)));
                    searchWords(res, curWords, paths, i + 1, visited, board);
                    curWords.remove(curWords.size() - 1);
                    for (int[] coor : paths.get(i)) {
                        visited[coor[0]][coor[1]] = false;
                    }
                }
            }
        }

        public List<String> fillInMostStr(char[][] board, Set<String> dict) {
            List<List<int[]>> paths = new ArrayList<>();

            Trie trie = new Trie();
            for (String word : dict) {
                trie.insert(word);
            }

            int m = board.length;
            int n = board[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    boolean[][] visited = new boolean[m][n];
                    visited[i][j] = true;
                    List<int[]> curPath = new ArrayList<>();
                    curPath.add(new int[]{i, j});
                    search(paths, board, i, j, trie, visited, curPath);
                }
            }

            List<String> res = new ArrayList<>();
            searchWords(res, new ArrayList<>(), paths, 0, new boolean[m][n], board);

            return res;
        }

        class SearchResult {
            boolean hasPrefix;
            boolean hasWord;

            SearchResult(boolean hasPrefix, boolean hasWord) {
                this.hasPrefix = hasPrefix;
                this.hasWord = hasWord;
            }
        }

        class TrieNode {
            char c;
            boolean isEnd;
            Map<Character, TrieNode> children;

            public TrieNode(char c, boolean isEnd) {
                this.c = c;
                this.isEnd = isEnd;
                this.children = new HashMap<>();
            }
        }

        class Trie {
            private TrieNode root;

            public Trie() {
                this.root = new TrieNode(' ', false);
            }

            public void insert(String word) {
                TrieNode cur = root;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if (!cur.children.containsKey(c)) {
                        cur.children.put(c, new TrieNode(c, false));
                    }
                    cur = cur.children.get(c);
                }
                cur.isEnd = true;
            }

            public SearchResult search(String word) {
                TrieNode cur = root;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if (!cur.children.containsKey(c)) {
                        return new SearchResult(false, false);
                    }
                    cur = cur.children.get(c);
                }
                return new SearchResult(true, cur.isEnd);
            }
        }
}


class TNode{
        TNode[] leaves;
        boolean isword;
        public TNode(){
                this.leaves=new TNode[26];
                this.isword=false;
        }
}

class BoggleGamePathLength {
//        public static void main(String[] args){
//                String[] dict={"abs","abc","dd","bb"};
//                char[][] mat={{'a','b','c'},{'d','d','d'},{'b','b','d'}};
//                BoggleGamePath sol=new BoggleGamePath();
//                System.out.println(sol.findmaxPath(dict,mat));
//        }
        
        private int max=0;
        private TNode root=new TNode();
        
        public int findmaxPath(String[] words, char[][] mat){
                for(int i=0;i<words.length;i++){
                        insertT(words[i]);
                }
                int range=mat.length*mat[0].length;
                HashSet<Integer> set=new HashSet<Integer>();
                for(int i=0;i<range;i++){
                        pathfind(set,this.root,mat,i,0);
                }
                return this.max;
        }
        
        private void pathfind(Set<Integer> set, TNode node, char[][] mat, int pos, int curcount){
                if(node.isword){
                        this.max=Math.max(max,curcount+1);
                        node.isword=false;
                        pathfind(set,this.root,mat,pos,curcount+1);
                        node.isword=true;
                }
                int x=pos/mat[0].length;
                int y=pos%mat[0].length;
                if(node.leaves[mat[x][y]-'a']!=null){
                        set.add(pos);
                        for(Integer connect:connected(pos,mat)){
                                if(!set.contains(connect)){
                                        pathfind(set,node.leaves[mat[x][y]-'a'],mat,connect,curcount);
                                }
                        }
                        set.remove(pos);
                }
                return;
        }
        
        private List<Integer> connected(int pos, char[][] mat){
                int m=mat.length;
                int n=mat[0].length;
                int x=pos/n;
                int y=pos%n;
                List<Integer> res=new ArrayList<Integer>();
                for(int i=-1;i<=1;i+=2){
                        if(x+i>=0&&x+i<m){
                                res.add((x+i)*n+y);
                        }
                        if(y+i>=0&&y+i<n){
                                res.add(x+y+i);
                        }
                }
                return res;
        }
        
        private void insertT(String s){
                TNode cur=this.root;
                for(int i=0;i<s.length();i++){
                        if(cur.leaves[s.charAt(i)-'a']==null){
                                 cur.leaves[s.charAt(i)-'a']=new TNode();
                        }
                        cur=cur.leaves[s.charAt(i)-'a'];
                        if(i==s.length()-1){
                                cur.isword=true;
                        }
                }
        }
}


class BoggleGamePath {
    public String longest = "";

//    public static void main(String[] args) {
//        String[] dict = {"abcddd","abc"};
//        char[][] mat = {{'a','b','c'},{'d','d','d'},{'b','b','d'}};
//        BoggleGamePath sol = new BoggleGamePath();
//        sol.findmaxPath(dict, mat);
//        System.out.println(sol.longest);
//
//        String[] dict2 = {"abs","abc","dd","bb"};
//        sol = new BoggleGamePath();
//        sol.findmaxPath(dict2, mat);
//        System.out.println(sol.longest);
//    }

    public void findmaxPath(String[] dict, char[][] mat) {
        Trie trie = new Trie();
        for (String s : dict) {
            trie.insert(s);
        }
        int rows = mat.length;
        int cols = mat[0].length;
        boolean[][] taken = new boolean[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                recursiveHelper(trie, mat, row, col, taken, "", "");
            }
        }
    }

    private void recursiveHelper(Trie trie, char[][] mat, int row, int col, boolean[][] taken, String prev, String current) {
        int rows = mat.length;
        int cols = mat[0].length;

        if (row < 0 || col < 0 || row >= rows || col >= cols || taken[row][col]) {
            return;
        }

        char ch = mat[row][col];
        taken[row][col] = true;
        current += ch;
        // Case 1 : use 'current' as the end of the prefix
        if (trie.search(current)) {
            if ((prev + current).length() > this.longest.length()) {
                this.longest = prev + current;
            }

            recursiveHelper(trie, mat, row + 1, col, taken, prev + current, "");
            recursiveHelper(trie, mat, row - 1, col, taken, prev + current, "");
            recursiveHelper(trie, mat, row, col + 1, taken, prev + current, "");
            recursiveHelper(trie, mat, row, col - 1, taken, prev + current, "");
        }

        // Case 2 : use 'current' as the start of the suffix
        // e.g. we should match 'abcddd' instead of 'abc' in dict
        if (trie.startsWith(current)) {
            recursiveHelper(trie, mat, row + 1, col, taken, prev, current);
            recursiveHelper(trie, mat, row - 1, col, taken, prev, current);
            recursiveHelper(trie, mat, row, col + 1, taken, prev, current);
            recursiveHelper(trie, mat, row, col - 1, taken, prev, current);
        }
        taken[row][col] = false;
    }
    
    class TrieNode {
        boolean isWord;
        TrieNode[] children;
        
        // Initialize your data structure here.
        public TrieNode() {
            children = new TrieNode[26];
        }
    }

    class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the trie.
        public void insert(String word) {
            if(word == null || word.length() == 0) return;
            TrieNode node = root;
            for(int i =0; i< word.length(); i++) {
                if(node.children[word.charAt(i) - 'a'] == null) {
                    node.children[word.charAt(i) - 'a'] = new TrieNode();
                }
                node = node.children[word.charAt(i) - 'a'];
            }
            node.isWord = true;
        }

        // Returns if the word is in the trie.
        public boolean search(String word) {
            if(word == null || word.length() == 0) return false;
            TrieNode node = root;
            for(int i= 0; i < word.length(); i++) {
                if(node.children[word.charAt(i) - 'a'] == null) return false;
                node = node.children[word.charAt(i) - 'a'];
            }
            return node.isWord;
        }

        // Returns if there is any word in the trie
        // that starts with the given prefix.
        public boolean startsWith(String prefix) {
            if(prefix == null || prefix.length() == 0) return false;
            TrieNode node = root;
            for(int i= 0; i < prefix.length(); i++) {
                if(node.children[prefix.charAt(i) - 'a'] == null) return false;
                node = node.children[prefix.charAt(i) - 'a'];
            }
            return true;
        }
    }
}



