// BFS, O(M*N), space O(1), onely one counter
// if 4 neighbors are 1, then minus the perimeter count

public class Solution {
    public int islandPerimeter(int[][] grid) {
	int count = 0;
	for (int i=0; i<grid.length; i++) {
		for (int j=0; j<grid[0].length; j++) {
			if (grid[i][j] == 1) {
				count+=4;
				if (i - 1 != -1 && grid[i-1][j] == 1) count--;
				if (i + 1 != grid.length && grid[i+1][j] == 1) count--;
				if (j - 1 != -1 && grid[i][j-1] == 1) count--;
				if (j + 1 != grid[0].length && grid[i][j+1] == 1) count--;
			}
		}
	}
	return count;
    }
}


// DFS O(M*N), space (M*N) worst case long line of dfs

public class Solution {
    public int islandPerimeter(int[][] grid) {
        if (grid == null) return 0;
        for (int i = 0 ; i < grid.length ; i++){
            for (int j = 0 ; j < grid[0].length ; j++){
                if (grid[i][j] == 1) {
                    return getPerimeter(grid,i,j);
                }
            }
        }
        return 0;
    }
    
    public int getPerimeter(int[][] grid, int i, int j){
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {return 1;}
        if (grid[i][j] == 0) {
            return 1;
        }
        if (grid[i][j] == -1) return 0;
        
        int count = 0;
        grid[i][j] = -1;
        
        count += getPerimeter(grid, i-1, j);
        count += getPerimeter(grid, i, j-1);
        count += getPerimeter(grid, i, j+1);
        count += getPerimeter(grid, i+1, j);
        
        return count;
        
    }
}
