public class Solution {
    public int search(int[] A, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        assert(A!=null);
       int start = 0, 
            end = A.length-1;
        while(start<=end){
            int mid = (start+end)/2;
            if(A[mid]==target)
                return mid;
            if(A[mid]>target && (A[end]>A[mid] || A[end]<target) 
                || A[mid]<target && A[end]>A[mid] && A[end]<target){
                    end=mid-1;
            }else{
                start=mid+1;
            }
         
        }
        return -1;
    }
}
