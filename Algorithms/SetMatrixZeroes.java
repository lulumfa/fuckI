// http://www.programcreek.com/2012/12/leetcode-set-matrix-zeroes-java/

// This problem can solve by following 4 steps:

// 1. check if first row and column are zero or not
// 2. mark zeros on first row and column
// 3. use mark to set elements
// 4. rst column and row by using marks in step 1

// O(m*n) space O(1)
public class Solution {
    public void setZeroes(int[][] matrix) {
        boolean firstRowZero = false;
        boolean firstColumnZero = false;
 
        //set first row and column zero or not
        for(int i=0; i<matrix.length; i++){
            if(matrix[i][0] == 0){
                firstColumnZero = true;
                break;
            }
        }
 
        for(int i=0; i<matrix[0].length; i++){
            if(matrix[0][i] == 0){
                firstRowZero = true;
                break;
            }
        }
        // can include index 0
        //mark zeros on first row and column
        for(int i=1; i<matrix.length; i++){
            for(int j=1; j<matrix[0].length; j++){
                if(matrix[i][j] == 0){
                   matrix[i][0] = 0;
                   matrix[0][j] = 0;
                }
            }
        }
        
        // cannot include index 0, because index o has been messed up with other cells, cannot tell the orginal zeroRow,zeroCol
        //use mark to set elements
        for(int i=1; i<matrix.length; i++){
            for(int j=1; j<matrix[0].length; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                   matrix[i][j] = 0;
                }
            }
        }
 
        //set first column and row
        if(firstColumnZero){
            for(int i=0; i<matrix.length; i++)
                matrix[i][0] = 0;
        }
 
        if(firstRowZero){
            for(int i=0; i<matrix[0].length; i++)
                matrix[0][i] = 0;
        }
 
    }
}
