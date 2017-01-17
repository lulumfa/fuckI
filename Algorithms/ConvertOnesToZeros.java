// O(mn) space(mn), BFS, using a set to contains masks that need to convert to zero

package Facebook;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class ConvertOneToZero {
	
	public static int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
	
	public static void main(String[] args) {
		int[][] matrix = {
				{0, 1, 0, 1, 1},
				{1, 0, 0, 0, 0},
				{0, 0, 1, 1, 0},
				{1, 0, 0, 1, 1},
				{0, 1, 0, 0, 0},
				{0, 0, 1, 1, 1}

		};
		ConvertOneToZero test = new ConvertOneToZero();
		int k = 5;
		test.convertOneToZero(matrix, k);
		
		for(int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}		
	}
	
	public void convertOneToZero(int[][] matrix, int k) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0 || k <= 1) return;
		
		int m = matrix.length, n = matrix[0].length;
		int mask = 2;
		HashSet<Integer> fewerThanK = new HashSet<Integer>();
		for(int i = 0; i < m; i++) {
			for(int j =0; j< n; j++) {
				if(matrix[i][j] == 1) {
					bfsMarkOnes(matrix, i, j, mask++, k, fewerThanK);
				}
 			}
		}
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(matrix[i][j] > 0) {
					if(fewerThanK.contains(matrix[i][j])) {
						matrix[i][j] = 0;
					} else {
						matrix[i][j] = 1;
					}
				}
			}
		}
	}
	
	private void bfsMarkOnes(int[][] matrix, int i, int j, int mask, int k, HashSet<Integer> fewerThanK) {
		Queue<Integer> queue = new LinkedList<Integer>();
		int m = matrix.length, n = matrix[0].length;
		int count = 1;
		queue.offer(i * n + j);
		matrix[i][j] = mask;
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			int oldX = cur /n;
			int oldY = cur%n;
			for(int[] dir : dirs) {
				int x = oldX + dir[0];
				int y = oldY + dir[1];

				if(x >=0 && x < m && y >=0 && y < n && matrix[x][y] == 1) {
					queue.offer(x * n + y);
					count++;
					matrix[x][y] = mask;
				}
			}
		}
		
		if(count < k) fewerThanK.add(mask);
	}
}
