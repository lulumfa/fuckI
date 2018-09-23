// bfs, O(E + V)

class Solution {
    
    private final static int[][] dirs = new int[][] {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
    
    public int slidingPuzzle(int[][] board) {
        if (board == null) return 0;
        String ans = hash(new int[][] {
            {1, 2, 3},
            {4, 5, 0}
        }, 2, 3);
        
        Set<String> visited = new HashSet<String>();
        int m = board.length, n = board[0].length;
        String start = hash(board, m, n);
        if (start.indexOf('0') < 0) return 0;
  
        int level = 0;
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        int size = 1;
        int newSize = 0;
        
        while (!queue.isEmpty()) {
            String curHash = queue.poll();
            int cur = curHash.indexOf('0');
            int i = cur/n, j = cur%n;
            size--;
            
            if (curHash.equals(ans)) return level;
            for (int[] dir : dirs) {
                int iNext = i + dir[0], jNext = j + dir[1];
                if (iNext >= 0 && iNext < m && jNext >=0 && jNext < n) {
                    char[] copy = curHash.toCharArray();
                    int next = iNext * n + jNext;
                    copy[cur] = copy[next];
                    copy[next] = '0';
                    String nextHash = String.valueOf(copy);
                    if (!visited.contains(nextHash)) {
                        visited.add(nextHash);
                        queue.offer(nextHash);
                        newSize++;
                    }
                }
            }
            
            if (size == 0) {
                size = newSize;
                newSize = 0;
                level++;
            }
        }
        
        return -1;
    }
    
    private String hash(int[][] board, int m, int n) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int i : row) {
                sb.append(i);
            }
        }
        
        return sb.toString();
    }
}
