//reference: https://oj.leetcode.com/problems/search-a-2d-matrix/

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0) return false;
        
        int l = 0, r = matrix.length-1;
        int mid;
        while(l<=r) {
            mid = (l+r)/2;
            if(matrix[mid][0]==target) return true;
            
            if(matrix[mid][0] >target) r =mid-1;
            else l = mid+1;
        }
        int ll = 0;
        if(r<0) return false;
        int rr = matrix[r].length-1;
        while(ll<=rr) {
            mid = (ll+rr)/2;
            if(matrix[r][mid] == target) return true;
            if(matrix[r][mid] > target) rr = mid-1;
            else ll = mid +1;
        }
        
        return false;
        
        
    }
}
