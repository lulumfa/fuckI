// constatnt time
// https://leetcode.com/problems/minimum-knight-moves/discuss/392053/Here-is-how-I-get-the-formula-(with-graphs)


// O(2D size n^2), same as size of the queue, whole n^2

//As hint suggests we can simulate all steps because the limits for the possible x and y are low (+-300). Also good catch is to work with abs(x) and abs(y) - it makes code simpler and doesn't affect the answer - just imagine that it's a mirrored image in case of negative x and y.

// We do the BFS style - from every cell we make all possible moves checking if we reach the target and if the cell has been visited before. If not - mark the cell as visited, store it in the BFS queue and continue the same loop.

// Because it's BFS we'll get the minimum number of moves. If we met this cell before that it's picked up by some other previous path and we can discard this current path.

// We store possible moves in 2D array, 8 elements that store increments of x and y coordinates. To store next cell for the BFS and visited cells we can use encoding - just multiply x by something > 600 (from -300 to 300) and add y. Multiplication can be replaced by bit shift - it's faster. 10 bits are enough - it gives 1024.
class Solution {
//store possible moves from one point as an array of changes in coordinates
    static int[][] d = new int[][] {
            {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}
    };

    public int minKnightMoves(int x, int y) {
        //we can invert the sign of the number - it doesn't affect the result
        x = Math.abs(x); y = Math.abs(y);
        //store seen cells
        Set<Integer> seen = new HashSet();
        //this is queue for the BFS, initialize it with 0,0 position
        Queue<Integer> q = new LinkedList();
        q.add(0);
        //this will store the number of moves
        int res = 0;
        //start BFS
        while (!q.isEmpty()) {
            //on each step we only poll number of cells that we have in the queue now.
            //everything added after this will be counted at the next step
            int size = q.size();
            for (int i = 0; i < size; i++) {
                //get the encoded num, convert it to coordinated and check if it's our target
                int next = q.poll();
                int curX = (next>>10), curY = next - (curX<<10);
                if (curX == x && curY == y)
                    return res;
                //if not the target - make all possible moves
                for (int k = 0; k < d.length; k++) {
                    //each next move
                    int x1 = curX + d[k][0], y1 = curY + d[k][1];
                    //encode the move to store it in the set of visited cells
                    int curEnc = y1 + (x1<<10);
                    if (x1 >= -2 && y1 >= -2 && seen.add(curEnc)) {
                        q.add(curEnc);
                    }
                }
            }
            //after we done with all moves from all points stored in te queue at the beggining -
            //increment the step counter
            res++;
        }
        return -1;
    }
}

// w/o  optimization of space traversal, the method above will only focus on one direction

class Solution {
    private final int[][] DIRECTIONS = new int[][] {{2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
    
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0});
        
        Set<String> visited = new HashSet<>();
        visited.add("0,0");
        
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.remove();
                int curX = cur[0];
                int curY = cur[1];
                if (curX == x && curY == y) {
                    return result;
                }
                
                for (int[] d : DIRECTIONS) {
                    int newX = curX + d[0];
                    int newY = curY + d[1];
                    if (!visited.contains(newX + "," + newY) && newX >= -1 && newY >= -1) {
                        queue.add(new int[] {newX, newY});
                        visited.add(newX + "," + newY);
                    }
                }
            }
            result++;
        }
        return -1;
    }
}
