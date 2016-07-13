//https://www.careercup.com/question?id=5162862051852288

import java.util.ArrayList;

public class ContinentalDivide {

    public static class Square {
        public boolean canReachPacific;
        public boolean canReachAtlantic;
        public boolean visited;

        public Square() {
            canReachPacific = false;
            canReachAtlantic = false;
            visited = false;
        }
    }

    private static class Position {
        public int row, col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public String toString() {
            return "(" + row + ", " + col + ")";
        }
    }

    public static ArrayList<Position> getContinentalDivide(int[][] height) {
        int nrows = height.length;
        int ncols = height[0].length;
        Square[][] grid = new Square[nrows][ncols];

        // create Square objects
        for (int row = 0; row < nrows; row++) {
            for (int col = 0; col < ncols; col++) {
                grid[row][col] = new Square();
            }
        }

        // mark .canReachPacific and .canReachAtlantic as true for edges
        for (int row = 0; row < nrows; row++) {
            grid[row][0].canReachPacific = true;
            grid[row][ncols-1].canReachAtlantic = true;
        }
        for (int col = 0; col < ncols; col++) {
            grid[0][col].canReachPacific = true;
            grid[nrows-1][col].canReachAtlantic = true;
        }

        // visit every square and drop water on it
        for (int row = 0; row < nrows; row++) {
            for (int col = 0; col < ncols; col++) {
                if (!grid[row][col].visited)
                    dropWater(row, col, grid, height);
            }
        }

        ArrayList<Position> divide = new ArrayList<Position>();
        for (int row = 0; row < nrows; row++) {
            for (int col = 0; col < ncols; col++) {
                if (grid[row][col].canReachPacific && grid[row][col].canReachAtlantic)
                    divide.add(new Position(row, col));
            }
        }

        return divide;
    }

    // drop water on a square, let it flow to surrounding squares if possible,
    // and then update the current square's .canReachPacific and .canReachAtlantic
    // instance variables
    public static void dropWater(int row, int col, Square[][] grid, int[][] height) {
        int nrows = height.length;
        int ncols = height[0].length;

        grid[row][col].visited = true;

        // up
        if (row != 0 && height[row][col] >= height[row-1][col]) {
            if (!grid[row-1][col].visited)
                dropWater(row-1, col, grid, height);
            grid[row][col].canReachPacific |= grid[row-1][col].canReachPacific;
            grid[row][col].canReachAtlantic |= grid[row-1][col].canReachAtlantic;
        }

        // down
        if (row != nrows-1  && height[row][col] >= height[row+1][col]) {
            if (!grid[row+1][col].visited)
                dropWater(row+1, col, grid, height);
            grid[row][col].canReachPacific |= grid[row+1][col].canReachPacific;
            grid[row][col].canReachAtlantic |= grid[row+1][col].canReachAtlantic;
        }

        // left
        if (col != 0 && height[row][col] >= height[row][col-1]) {
            if (!grid[row][col-1].visited)
                dropWater(row, col-1, grid, height);
            grid[row][col].canReachPacific |= grid[row][col-1].canReachPacific;
            grid[row][col].canReachAtlantic |= grid[row][col-1].canReachAtlantic;
        }

        // right
        if (col != ncols-1 && height[row][col] >= height[row][col+1]) {
            if (!grid[row][col+1].visited)
                dropWater(row, col+1, grid, height);
            grid[row][col].canReachPacific |= grid[row][col+1].canReachPacific;
            grid[row][col].canReachAtlantic |= grid[row][col+1].canReachAtlantic;
        }
    }

    public static void main(String[] args) {
        int[][] height = {
            {1, 2, 2, 3, 5},
            {3, 2, 3, 4, 4},
            {2, 4, 5, 3, 1},
            {6, 7, 1, 4, 5},
            {5, 1, 1, 2, 4}
        };

        ArrayList<Position> divide = getContinentalDivide(height);

        for (Position p : divide)
            System.out.println(p);
    }

}
