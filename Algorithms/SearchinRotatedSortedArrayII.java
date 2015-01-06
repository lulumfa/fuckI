//reference: http://blog.csdn.net/linhuanmars/article/details/20588511

public class Solution {
    public boolean search(int[] A, int target) {
        if(A==null || A.length==0) return false;
        
        int l = 0;
        int r = A.length-1;
        
        while(l<=r) {
            int m = (l+r)/2;
            if(A[m]==target) return true;
            while(l<m && A[l]==A[m]) {
                l++;
            }
            if(A[l] <= A[m]) {
                if(target >= A[l] && target <=A[m]) r = m-1;
                else l = m+1;
            } else {
                if(target >=A[m] && target<=A[r]) l = m+1;
                else r = m-1;
            }
        }
        return false;
    }
}
