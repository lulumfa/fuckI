// O(mn), space O(m+n)

public class Solution {
    private static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public void wallsAndGates(int[][] rooms) {
        if(rooms == null || rooms.length == 0 || rooms[0].length == 0) return;
        int m = rooms.length, n = rooms[0].length;
        Queue<Integer> queue = new LinkedList<Integer>();
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(rooms[i][j] == 0) {
                    queue.offer(i * n + j);
                }
            }
        }
        
        while(!queue.isEmpty()) {
            int curPoint = queue.poll();
            int x = curPoint/n;
            int y = curPoint%n;
            for(int[] dir : dirs) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if(isValid(newX, newY, rooms)) {
                    rooms[newX][newY] = rooms[x][y] + 1;
                    queue.offer(newX * n + newY);
                }
            }
        }
    }
    
    private boolean isValid(int x, int y, int[][] rooms) {
        return x >=0 && x < rooms.length && y >=0 && y < rooms[0].length && rooms[x][y] == Integer.MAX_VALUE;  
    }
}

public class Solution {
    private static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public void wallsAndGates(int[][] rooms) {
        if(rooms == null || rooms.length == 0 || rooms[0].length == 0) return;
        int m = rooms.length, n = rooms[0].length;
        Queue<Integer> queue = new LinkedList<Integer>();
        
        int pre = 0, cur = 0, level = 1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(rooms[i][j] == 0) {
                    queue.offer(i * n + j);
                    pre++;
                }
            }
        }
        
        while(!queue.isEmpty()) {
            int curPoint = queue.poll();
            pre--;
            int x = curPoint/n;
            int y = curPoint%n;
            for(int[] dir : dirs) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if(isValid(newX, newY, rooms)) {
                    rooms[newX][newY] = level;
                    cur++;
                    queue.offer(newX * n + newY);
                }
            }
            
            if(pre==0) {
                level++;
                pre = cur;
                cur = 0;
            }
        }
    }
    
    private boolean isValid(int x, int y, int[][] rooms) {
        return x >=0 && x < rooms.length && y >=0 && y < rooms[0].length && rooms[x][y] == Integer.MAX_VALUE;  
    }
}
