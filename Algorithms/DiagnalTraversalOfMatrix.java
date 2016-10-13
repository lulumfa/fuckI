O(n2) space O(1)

package Leetcode;

public class DiagnalTraversalOfMatrix {
	
	public static void main(String[] args) {
//		int[][] matrix = {
//				{
//					1, 2, 3, 4
//				},
//				{
//					5, 6, 7, 8
//				},
//				{
//					9, 10, 11, 12
//				},
//				{
//					13, 14, 15, 16
//				}
//		};
		
		int[][] matrix = {
				{
					1, 2, 3, 4
				},
				{
					5, 6, 7, 8
				},
				{
					9, 10, 11, 12
				}
		};
		
//		int[][] matrix = {
//				{
//					1, 2, 3
//				},
//				{
//					5, 6, 7
//				},
//				{
//					9, 10, 11
//				},
//				{
//					13, 14, 15
//				}
//		};
//		printDiagnalFromRight(matrix);
		printDiagnalFromLeft(matrix);
	}
	
	public static void printDiagnalFromLeft(int[][] matrix) {
		if(matrix == null) return;
		
		int size = matrix.length + matrix[0].length -1;
		int line = size * 2 -1;
		
		for(int k = 0; k < line; k++) {
			for(int i = 0; i < matrix.length; i++) {
				if((k-i) >=0 && (k -i) < matrix[0].length) System.out.print(matrix[i][k-i] + " ");
			}
			System.out.println("");
		}
	}
	
	public static void printDiagnalFromRight(int[][] matrix) {
		if(matrix == null) return;
		
		int size = matrix.length + matrix[0].length -1;
		int line = size * 2 -1;
		
		for(int k = 0; k < line; k++) {
			for(int i = 0; i < matrix.length; i++) {
				if((i + size -1 - k) >=0 && (i + size -1 - k) < matrix[0].length) 
					System.out.print(matrix[i][i + size -1 - k] + " ");
			}
			System.out.println("");
		}		
	}
}
