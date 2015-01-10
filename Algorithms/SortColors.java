//reference: http://blog.csdn.net/linhuanmars/article/details/24286349

public class Solution {
    public void sortColors(int[] A) {
        if(A==null) return;
        
        int end0 = 0, end1 = 0;
        
        for(int i = 0; i<A.length; i++) {
            if(A[i]==0) {
                A[i] = 2;
                A[end1++] = 1;
                A[end0++] = 0;
            } else if(A[i] ==1) {
                A[i] =2;
                A[end1++] = 1;
            }
        }
    }
}
