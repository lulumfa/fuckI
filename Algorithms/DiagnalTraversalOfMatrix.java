// 从上往下， 从左往右或者从右往左， 【1】，【2， 5】， 【3， 6， 9】， etc or 【4】【3， 8】

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
		int line = size * 2 -1; // should be just size?
		
		for(int k = 0; k < line; k++) {
			for(int i = 0; i < matrix.length; i++) {
				if((k-i) >=0 && (k -i) < matrix[0].length) System.out.print(matrix[i][k-i] + " ");
			}
			System.out.println("");
		}
	}
	
	// more straightforward way, scannig from starting points of first row and last column
 public static void printDiagnalFromLeft2(int[][] matrix) {
    if(matrix == null) return;

    // top rown
    for (int jStart = 0, i = 0; jStart < matrix[0].length; jStart++) {
      int j = jStart;
      while(j >= 0 && i < matrix.length) {
        System.out.print(matrix[i][j] + " ");
        j--;
        i++;
      }
      System.out.println();

      i = 0;
    }

    // right row
    for (int iStart = 1, j = matrix[0].length -1; iStart < matrix.length; iStart++) {
      int i = iStart;
      while(j >= 0 && i < matrix.length) {
        System.out.print(matrix[i][j] + " ");
        j--;
        i++;
      }
      System.out.println();
      j = matrix[0].length -1;
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
