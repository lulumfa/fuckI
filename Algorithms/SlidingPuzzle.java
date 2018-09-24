// bfs, O(E + V), might not be correct, as the board keeps mutating and the upper bound of all possibility 
// would be k = m*n, Ak = k*(k-1)(k-2)...1 = O(k!) = O(m!n! * m!n!) and easily overflow, might need to A* solution here later, big-O same?

package Airbnb;

import java.util.*;

public class SlidingPuzzle {

	public static void main(String[] args) {
		SlidingPuzzle sp = new SlidingPuzzle();
//		// valid more nums case
//		int[][] board = new int[][] {
//			{1, 2, 3},
//			{4, 0, 5},
//			{7, 8, 6}, 
//			{10, 11, 9}
//		};
		
		// large board, invalid case, easy to overflow
		int[][] board = new int[][] {
			{1, 2, 3},
			{4, 0, 5},
			{7, 8, 6}, 
			{11, 10, 9}
		};
		
//		// small board, invalid case
//		int[][] board = new int[][] {
//			{1, 2, 3},
//			{5, 4, 0}
//		};
		
		System.out.println(sp.slidingPuzzleBoolean(board));
//		System.out.println(Arrays.toString(sp.slidingPuzzleWithSteps(board)));
	}
    
	public boolean slidingPuzzleBoolean(int[][] board) {
		if (board == null) return false;
        int m = board.length, n = board[0].length;
        
        Queue<Footprint> queue = new LinkedList<Footprint>();
        Set<String> visited = new HashSet<String>();
        Footprint start = null;
        
        int[][] ans = new int[m][n];
        // find start point
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = (i == m -1 && j == n -1) ? 0 : i * n + j + 1;
                if (board[i][j] == 0) {
                    start = new Footprint(i * n + j, serialize(board, m, n), new ArrayList<Integer[]>());
                }
            }
        }
        String ansString = serialize(ans, m, n);
        if (start == null) return false;
        queue.offer(start);
        visited.add(start.boardString);
        
        while(!queue.isEmpty()) {
            Footprint cur = queue.poll();
            int x = cur.pos / n, y = cur.pos % n;
            
            if (cur.boardString.equals(ansString)) {
                return true;    
            }
            
            System.out.println(Arrays.deepToString(deserialize(cur.boardString, m , n)));
            
            for (Integer[] dir : dirs) {
                int xNext = x + dir[0], yNext = y + dir[1];
                if (xNext >= 0 && xNext < m && yNext >= 0 && yNext < n) {
                    int[][] curBoard = deserialize(cur.boardString, m ,n);
                    curBoard[x][y] = curBoard[xNext][yNext];
                    curBoard[xNext][yNext] = 0;
                    String boardString = serialize(curBoard, m, n);
                    if (!visited.contains(boardString)) {
                        visited.add(boardString);
                        queue.offer(new Footprint(xNext * n + yNext, boardString, new ArrayList<Integer[]>(cur.steps) {{
                            add((Integer[])dir);
                        }}));
                    }
                }
            }
        }
        	    
	    return false;
	}

private final static Integer[][] dirs = new Integer[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
    public int slidingPuzzle(int[][] board) {
        if (board == null) return 0;
        int m = board.length, n = board[0].length;
        
        Queue<Footprint> queue = new LinkedList<Footprint>();
        Set<String> visited = new HashSet<String>();
        Footprint start = null;
        
        int[][] ans = new int[m][n];
        // find start point
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = (i == m -1 && j == n -1) ? 0 : i * n + j + 1;
                if (board[i][j] == 0) {
                    start = new Footprint(i * n + j, serialize(board, m, n), new ArrayList<Integer[]>());
                }
            }
        }
        String ansString = serialize(ans, m, n);
        if (start == null) return -1;
        queue.offer(start);
        visited.add(start.boardString);
        int level = 0;
        int size = 1;
        int newSize = 0;
        
        while(!queue.isEmpty()) {
            Footprint cur = queue.poll();
            int x = cur.pos / n, y = cur.pos % n;
            size--;
            
            if (cur.boardString.equals(ansString)) {
                return level;    
            }
            
            for (Integer[] dir : dirs) {
                int xNext = x + dir[0], yNext = y + dir[1];
                if (xNext >= 0 && xNext < m && yNext >= 0 && yNext < n) {
                    int[][] curBoard = deserialize(cur.boardString, m ,n);
                    curBoard[x][y] = curBoard[xNext][yNext];
                    curBoard[xNext][yNext] = 0;
                    String boardString = serialize(curBoard, m, n);
                    if (!visited.contains(boardString)) {
                        visited.add(boardString);
                        queue.offer(new Footprint(xNext * n + yNext, boardString, new ArrayList<Integer[]>(cur.steps) {{
                            add((Integer[])dir);
                        }}));
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
    
    public String[] slidingPuzzleWithSteps(int[][] board) {
        if (board == null) return null;
        int m = board.length, n = board[0].length;
        
        Queue<Footprint> queue = new LinkedList<Footprint>();
        Set<String> visited = new HashSet<String>();
        Footprint start = null;
        
        int[][] ans = new int[m][n];
        // find start point
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = (i == m -1 && j == n -1) ? 0 : i * n + j + 1;
                if (board[i][j] == 0) {
                    start = new Footprint(i * n + j, serialize(board, m, n), new ArrayList<Integer[]>());
                }
            }
        }
        String ansString = serialize(ans, m, n);
        if (start == null) return null;
        queue.offer(start);
        visited.add(start.boardString);
        
        while(!queue.isEmpty()) {
            Footprint cur = queue.poll();
            int x = cur.pos / n, y = cur.pos % n;
            
            if (cur.boardString.equals(ansString)) {
                return formatSteps(cur.steps);    
            }
            
            for (Integer[] dir : dirs) {
                int xNext = x + dir[0], yNext = y + dir[1];
                if (xNext >= 0 && xNext < m && yNext >= 0 && yNext < n) {
                    int[][] curBoard = deserialize(cur.boardString, m ,n);
                    curBoard[x][y] = curBoard[xNext][yNext];
                    curBoard[xNext][yNext] = 0;
                    String boardString = serialize(curBoard, m, n);
                    if (!visited.contains(boardString)) {
                        visited.add(boardString);
                        queue.offer(new Footprint(xNext * n + yNext, boardString, new ArrayList<Integer[]>(cur.steps) {{
                            add((Integer[])dir);
                        }}));
                    }
                }
            }
        }
        
        return null;
    }
    
    //private final static Integer[][] dirs = new Integer[][] {{1, 0} // down, {0, 1} // right, {-1, 0} // up, {0, -1} // left};
    private String[] formatSteps(List<Integer[]> steps) {
		if (steps == null) return null;
		String[] res = new String[steps.size()];
		int i = 0;
		for (Integer[] step : steps) {
			if (Arrays.equals(dirs[0], step)) {
				res[i++] = "Down";
			} else if (Arrays.equals(dirs[1], step)) {
				res[i++] = "Right";
			} else if (Arrays.equals(dirs[2], step)) {
				res[i++] = "Up";
			} else if (Arrays.equals(dirs[3], step)) {
				res[i++] = "Left";
			}
		}
		return res;
	}

	private String serialize(int[][] board, int m, int n) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int i : row) {
                sb.append(i).append(",");
            }
        }
        sb.setLength(sb.length() -1);
        return sb.toString();
    }
    
    private int[][] deserialize(String boardString, int m, int n) {
        String[] nums = boardString.split(",");
        int[][] board = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.valueOf(nums[i* n + j]);
            }
        }
        return board;
    }
}
class Footprint {
    int pos;
    String boardString;
    List<Integer[]> steps; // steps of dirs
    
    public Footprint(int pos, String boardString, List<Integer[]> steps) {
        this.pos = pos;
        this.boardString = boardString;
        this.steps = steps;
    }
}
