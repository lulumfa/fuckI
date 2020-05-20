/* [
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
*/
// [1,2,4,7,5,3,6,8,9], first top and then down, from top left corner
// if we want to change order, just need to switch the r+c)%2==0 to r+c)%2== 1 

// if we want to slice levels, just create a new list when doing those  the == 0 or == cols -1
// O(M*N), space constant
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0) return new int[0];
        int r=0,c=0;
        int rows=matrix.length,cols=matrix[0].length;
        int[] res=new int[rows*cols];
        for(int i=0;i<res.length;i++){
            res[i]=matrix[r][c];
            if((r+c)%2==0){
                if(c==cols-1) r++;
                else if(r==0) c++;
                else{
                    c++;
                    r--;
                }
            }
            else{
                if(r==rows-1) c++;
                else if(c==0) r++;
                else{
                    r++;
                    c--;
                }
            }
        }
        return res;
    }
}
