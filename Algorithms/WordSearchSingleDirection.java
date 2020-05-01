// O(mn * max(m,n) + l*k) k = length of word, building trie = O(l*k), l = words.length, 
// max(m,n) because the longest word can be put in the board is this len

!!!// we can further optimize it by not putting words longer than max(m, n) in the trie

package snap;

import java.util.ArrayList;
import java.util.List;

public class WordSearchSingleDirection {

  private static final int[][] dirs = {{-1, 0}, {-1, -1}, {-1, 1}, {0, 1}, {0, -1}, {1, 0}, {1, 1}, {1, -1}};

  public static void main(String[] args) {

    char[][] board = new char[][]{
        {
          'A', 'T', 'C'
        },
        {
          'C', 'A', 'Y'
        },
        {
          'T', 'B', 'B'
        }
    };

    String[] words = new String[] {"cat", "bat"};

    WordSearchSingleDirection wordSearchSingleDirection = new WordSearchSingleDirection();

    wordSearchSingleDirection.markNonWordsWithDot(board, words);

    for (int i = 0; i < board.length; i++) {
      System.out.println(board[i]);
    }
  }

  public void markNonWordsWithDot(char[][] board, String[] words) {
    if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) return;

    TrieNode root = buildTrie(words);

    boolean[][] used = new boolean[board.length][board[0].length];

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (root.children[board[i][j] - 'A'] != null) {
          for (int[] dir : dirs) {
            dfsFindWord(board, used, root.children[board[i][j] - 'A'], i, j, dir, new ArrayList<>());
          }
        }
      }
    }

    for (int i = 0; i < used.length; i++) {
      for (int j = 0; j < used[0].length; j++) {
        if (!used[i][j]) {
          board[i][j] = '.';
        }
      }
    }
  }

  private void dfsFindWord(char[][] board, boolean[][] used, TrieNode node, int x, int y, int[] dir, List<int[]> path) {
    path.add(new int[]{x, y});

    if (node.isWord) {
      for (int[] step : path) {
        used[step[0]][step[1]] = true;
      }
    }

    char c = board[x][y];
    board[x][y] = '\u0000';

    int xx = x + dir[0], yy = y + dir[1];

    if (xx >= 0 && xx < board.length && yy >=0 && yy < board[0].length && node.children[board[xx][yy] - 'A'] != null) {
      dfsFindWord(board, used, node.children[board[xx][yy] - 'A'], xx, yy, dir, path);
    }

    board[x][y] = c;
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
      p.isWord = true;
    }

    return root;
  }
}

class TrieNode {
  boolean isWord;
  TrieNode[] children;

  public TrieNode() {
    this.children = new TrieNode[26];
  }
}
