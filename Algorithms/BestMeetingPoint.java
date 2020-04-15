// O(mn), space (max(m, n))
class Solution {
    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length ==0) return -1;
        
        List<Integer> rows = findAllRowPos(grid);
        List<Integer> cols = findAllColPos(grid);
        
        int dist = minTravelDistance(rows);
        dist += minTravelDistance(cols);
        return dist;
    }
    
    private List<Integer> findAllRowPos(int[][] grid) {
        List<Integer> rows = new ArrayList<Integer>();
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    rows.add(i);
                }
            }
        }
        return rows;
    }
    
    private List<Integer> findAllColPos(int[][] grid) {
        List<Integer> cols = new ArrayList<Integer>();
        
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[j][i] == 1) {
                    cols.add(i);
                }
            }
        }
        return cols;
    }
    
    
    private int minTravelDistance(List<Integer> list) {
        if (list == null || list.size() == 0) return -1;
        
        int left = 0, right = list.size() -1;
        int dist = 0;
        
        while (left < right) {
            dist += list.get(right) - list.get(left);
            left++;
            right--;
        }
        return dist;
    }
}
