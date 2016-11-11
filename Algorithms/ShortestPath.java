package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://www.careercup.com/question?id=5725353829990400
public class ShortePath {
	public static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	private char[][] matrix;
	Queue<Position> queue;
	
	public static void main(String[] args) {
		char[][] matrix = {    
				{'1','1','1','1','1'},
                {'1','S','X','1','1'},
                {'1','1','1','1','1'},
                {'X','1','1','E','1'},
                {'1','1','1','1','X'}   
        };
		
		ShortePath sp = new ShortePath(matrix);
		System.out.println(Arrays.toString(sp.findShortestPath()));
	}	
	
	public ShortePath(char[][] matrix){
		this.matrix = matrix;
	}
	
	public Position[] findShortestPath() {
		if(matrix == null || matrix.length ==0 || matrix[0].length == 0) return null;
		Position[] path = null;
		
		Position start = findStart();
		if(start == null) return null;
		
		Queue<Position> queue = new LinkedList<Position>();
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			Position cur = queue.poll();
			System.out.println(cur);
			if(isEndFound(cur)) {
				path = backTrackPath(cur);
				break;
			} else {
				for(int i = 0; i < dirs.length; i++) {
					Position p = new Position(cur.x + dirs[i][0], cur.y + dirs[i][1], cur);
					if(isValidMove(p)) {
						queue.offer(p);
						moveForward(p);
					}
				}
			}
		}
		
		return path;
	}
	
	private Position[] backTrackPath(Position cur) {
		Position[] res;
		List<Position> container = new ArrayList<Position>();
		while(cur != null) {
			container.add(cur);
			cur = cur.predecessor;
		}
		res = new Position[container.size()];
		for(int i = container.size()-1; i>=0; i--) {
			res[container.size() -1 -i] = container.get(i);
		}
		return res;
	}
	
	private Position findStart() {
		for(int i = 0; i< matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				if(matrix[i][j] == 'S') return new Position(i, j);
			}
		}
		return null;
	}
	
	private void moveForward(Position p) {
		if(matrix[p.x][p.y] != 'E') matrix[p.x][p.y] = 'V';
	}
	
	private boolean isValidMove(Position p) {
		return p.x >=0 && p.x < matrix.length && 
			   p.y >=0 && p.y < matrix[0].length &&
			   (matrix[p.x][p.y] == '1' || matrix[p.x][p.y] == 'E');
	}
	
	private boolean isEndFound(Position p) {
		return matrix[p.x][p.y] == 'E';
	}
}

class Position {
	int x;
	int y;
	Position predecessor;
	
	public Position(int x, int y, Position predecessor) {
		this.x = x;
		this.y = y;
		this.predecessor = predecessor;
	}
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "[" + x + ", " + y+ "] ";
	}
}
