// reference: http://blog.csdn.net/linhuanmars/article/details/19712333

public class Solution {
    public void merge(int A[], int m, int B[], int n) {
        if(A==null || B==null) return;
        int i = m+n-1;
        int j = m-1;
        int k = n-1;
        while( j>=0 && k >=0) {
            if(A[j]<B[k]) {
                A[i--] = B[k--];
            } else {
                A[i--] = A[j--];
            }
        }
        while(k>=0) {
            A[i--] = B[k--];
        }
    }
}
