//https://segmentfault.com/a/1190000003753307
//时间 O(NM) 空间 O(max(N,M)) 递归栈空间



public class Solution {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length ==0 || grid[0].length ==0) return 0;
        int res=  0;
        for(int i =0; i< grid.length; i++){
            for(int j = 0; j< grid[0].length; j++) {
                if(grid[i][j] == '1'){
                    res++;
                    bfsFillZero(grid, i, j);
                }
            }
        }
        return res;
    }
    
    private void bfsFillZero(char[][] board, int i, int j){
        board[i][j] = '0';
        if(i>0 && board[i-1][j] == '1') bfsFillZero(board, i-1, j);
        if((i+1) < board.length && board[i+1][j] == '1') bfsFillZero(board, i+1, j);
        if(j>0 && board[i][j-1] == '1') bfsFillZero(board, i, j-1);
        if((j+1) < board[0].length && board[i][j+1] == '1') bfsFillZero(board, i, j+1);
    }
}

//number of lakes

public class NumberOfLakes {
    
    public static void main(String[] args){
        NumberOfLakes nof = new NumberOfLakes();
        int[][] world = {{1,1,1,0,1},{1,0,0,1,0},{1,1,1,1,1,},{0,1,1,0,1},{0,1,1,1,1}};
        System.out.println(nof.numberOfLakes(world));
    }
    
    public int numberOfLakes(int[][] world){
        int islandId = 2;
        for(int i = 0; i < world.length; i++){
            for(int j = 0; j < world[0].length; j++){
                if(world[i][j] == 1){
                    findIsland(world, i, j, islandId);
                    islandId++;
                }
            }
        }
        int lake = 0;
        for(int i = 0; i < world.length; i++){
            for(int j = 0; j < world[0].length; j++){
                if(world[i][j] == 0){
                    // 找到当前水域的邻接陆地编号
                    int currId = 0;
                    if(i > 0) currId = (world[i - 1][j] != 0 ? world[i - 1][j] : currId);
                    if(j > 0) currId = (world[i][j - 1] != 0 ? world[i][j - 1] : currId);
                    if(i < world.length - 1) currId = (world[i + 1][j] != 0 ? world[i + 1][j] : currId);
                    if(j < world[0].length - 1) currId = (world[i][j + 1] != 0 ? world[i][j + 1] : currId);
                    // 如果该点是湖，加1
                    if(findLake(world, i, j, currId)){
                        lake++;
                    }
                }
            }
        }
        return lake;
    }
    
    private boolean findLake(int[][] world, int i, int j, int id){
        // 将当前水域标记成周边岛屿的数字
        world[i][j] = id;
        // 找上下左右是否是同一块岛屿的陆地，如果是水域则继续DFS，如果当前水域是边界也说明不是湖
        boolean up = i != 0 
                && (world[i - 1][j] == id 
                || (world[i - 1][j] == 0 && findLake(world, i - 1, j, id)));
        boolean down = i != world.length - 1 
                && (world[i + 1][j] == id 
                || (world[i + 1][j] == 0 && findLake(world, i + 1, j, id)));
        boolean left = j != 0 
                && (world[i][j - 1] == id 
                || (world[i][j - 1] == 0 && findLake(world, i, j - 1, id)));
        boolean right = j != world[0].length - 1 
                && (world[i][j + 1] == id 
                || (world[i][j + 1] == 0 && findLake(world, i, j + 1, id)));
        return up && down && right && left;
    }
    
    private void findIsland(int[][] world, int i, int j, int id){
        world[i][j] = id;
        if(i > 0 && world[i - 1][j] == 1) findIsland(world, i - 1, j, id);
        if(j > 0 && world[i][j - 1] == 1) findIsland(world, i, j - 1, id);
        if(i < world.length - 1 && world[i + 1][j] == 1) findIsland(world, i + 1, j, id);
        if(j < world[0].length - 1 && world[i][j + 1] == 1) findIsland(world, i, j + 1, id);
    }
}
