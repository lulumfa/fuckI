// O(mn) ,space O(mn)
// be careful not to consider it a stop if detecting visited point, only stop when wall
class Solution {
    
    private final static int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze == null || start == null || destination == null || start.length != 2 || destination.length != 2) return false;
        
        int m = maze.length, n = maze[0].length;
        boolean[] visited = new boolean[m*n];
        Queue<Integer> queue = new LinkedList<Integer>();
        int startP = start[0] * n + start[1];
        queue.offer(startP);
        visited[startP] = true;
        
        while(!queue.isEmpty()) {
            int curP = queue.poll();
            int x = curP/n, y = curP % n;
            if (x == destination[0] && y == destination[1]) return true;
            for (int[] dir : dirs) {
                int xx = x + dir[0];
                int yy = y + dir[1];
                
                while (isValidMove(maze, m, n, xx, yy, visited)) {
                    xx += dir[0];
                    yy += dir[1];
                }
                if (xx >=0 && xx < m && yy >=0 && yy < n && visited[xx * n + yy]) continue; // this is the key

                xx -= dir[0];
                yy -= dir[1];
                int nextP = xx * n + yy;
                if (!visited[xx * n + yy]) {
                    visited[xx * n + yy] = true;
                    queue.offer(nextP);
                }
            }
        }
        
        return false;
    }
    
    private boolean isValidMove(int[][] maze, int m, int n, int xx, int yy, boolean[] visited) {
        return xx >=0 && xx < m && yy >=0 && yy < n && !visited[xx * n + yy] && maze[xx][yy] != 1; 
    }
}
