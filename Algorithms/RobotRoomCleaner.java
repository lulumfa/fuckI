// constatnt time
// https://leetcode.com/problems/minimum-knight-moves/discuss/392053/Here-is-how-I-get-the-formula-(with-graphs)

// O(4^(n-m)), n is # of cell, m is # of obstacles
// space O(n -m), since we only track visited cells
// direction, 0, 1, 2, 3, top, bot, left, right ,does not matter
// https://leetcode.com/problems/robot-room-cleaner/discuss/151942/Java-DFS-Solution-with-Detailed-Explanation-and-6ms-(99)-Solution

class Solution {
    final int[][] directions = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
    private void find(Robot robot, Set<String> visited, int curDirection, int row, int col){
        StringBuilder sb= new StringBuilder();
        sb.append(row);
        sb.append(">");
        sb.append(col);
        visited.add(sb.toString());
        robot.clean();
        for(int i=0; i<4;++i){
            int direction= (curDirection+i)%4;
            int [] next = directions[direction];
            int nextRow= row+next[0];
            int nextCol = col+next[1];
            sb = new StringBuilder();
            sb.append(nextRow);
            sb.append(">");
            sb.append(nextCol);
            if(!visited.contains(sb.toString()) && robot.move()){
                find(robot, visited, direction, nextRow, nextCol);
                robot.turnLeft();
                robot.turnLeft();
                robot.move();
                robot.turnLeft();
                robot.turnLeft();
            }
            robot.turnRight();
        }
    }
    
    public void cleanRoom(Robot robot) {
        Set<String> offset = new HashSet<>();
        find(robot,offset,0,0,0);
    }
}

// optimization

class Solution {
    final int[][] directions = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
    class Node{
        int row;
        int col;
        public Node(int row, int col){
            this.row=row;
            this.col=col;
        }
        @Override
        public boolean equals(Object o){
            Node node = (Node)o;
            if(node.row==row && node.col==col){
                return true;
            }
            return false;
        }
        @Override
        public int hashCode(){
            int res = 17;
            res=res*31+row;
            res=res*31+col;
            return res;
        }
    }
    private void find(Robot robot, Set<Node> visited, int curDirection, int row, int col){
        Node node = new Node(row,col);
        visited.add(node);
        robot.clean();
        for(int i=0; i<4;++i){
            int direction= (curDirection+i)%4;
            int [] next = directions[direction];
            int nextRow= row+next[0];
            int nextCol = col+next[1];
            node = new Node(nextRow,nextCol);
            if(!visited.contains(node) && robot.move()){
                find(robot, visited, direction, nextRow, nextCol);
                robot.turnLeft();
                robot.turnLeft();
                robot.move();
                robot.turnLeft();
            }
            else{
                robot.turnRight();
            }
        }
    }
    
    public void cleanRoom(Robot robot) {
        Set<Node> offset = new HashSet<>();
        find(robot,offset,0,0,0);
    }
}
