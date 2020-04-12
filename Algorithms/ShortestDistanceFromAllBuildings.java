// 
class Solution {
    private static int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return -1;
        
        int m = grid.length, n = grid[0].length;
        int[][] dist = new int[m][n];
        int[][] reached = new int[m][n];
        
        int totalBuildings = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    totalBuildings++;
                    
                    Queue<int[]> queue = new LinkedList<int[]>();
                    queue.offer(new int[]{i, j});
                    boolean[][] visited = new boolean[m][n];
                    visited[i][j] = true;
                    int level = 0;
                    while(!queue.isEmpty()) {
                        int size = queue.size();
                        for (int k = 0; k < size; k++) {
                            int[] cur = queue.poll();
                            int x = cur[0], y = cur[1];

                            for (int[] dir : dirs) {
                                int xx = x + dir[0], yy = y + dir[1];
                                if (xx >= 0 && xx < m && yy >= 0 && yy < n && grid[xx][yy] == 0 && !visited[xx][yy]) {
                                    visited[xx][yy] = true;
                                    queue.offer(new int[]{xx, yy});
                                    dist[xx][yy] += (level +1) ;
                                    reached[xx][yy] += 1;
                                }
                            }
                        }
                        level++;   
                    }
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && reached[i][j] == totalBuildings) {
                    min = Math.min(min, dist[i][j]);
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
