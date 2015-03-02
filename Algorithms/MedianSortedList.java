// reference: http://blog.csdn.net/linhuanmars/article/details/19905515

public double findMedianSortedArrays(int A[], int B[]) {
    if((A.length+B.length)%2==1)
        return helper(A,B,0,A.length-1,0,B.length-1,(A.length+B.length)/2+1);
    else
        return (helper(A,B,0,A.length-1,0,B.length-1,(A.length+B.length)/2)  
               +helper(A,B,0,A.length-1,0,B.length-1,(A.length+B.length)/2+1))/2.0;
}
private int helper(int A[], int B[], int i, int i2, int j, int j2, int k)
{
    int m = i2-i+1;
    int n = j2-j+1;
    if(m>n)
        return helper(B,A,j,j2,i,i2,k);
    if(m==0)
        return B[j+k-1];
    if(k==1)
        return Math.min(A[i],B[j]);
    int posA = Math.min(k/2,m);
    int posB = k-posA;
    if(A[i+posA-1]==B[j+posB-1])
        return A[i+posA-1];
    else if(A[i+posA-1]<B[j+posB-1])
        return helper(A,B,i+posA,i2,j,j+posB-1,k-posA);
    else
        return helper(A,B,i,i+posA-1,j+posB,j2,k-posB);
}
package leetcode;


public class MedianSortedList {
    public double findMedianSortedArrays(int A[], int B[]) {
        int m = A.length;
        int n = B.length;
        int median1 = (m + n) / 2 + 1;
        int median2 = (m + n - 1) / 2 + 1;
        return (findKth(A, 0, m, B, 0, n, median1) + findKth(A, 0, m, B, 0, n,
                median2)) / 2f;
    }

    private int findKth(int a[], int as, int ae, int b[], int bs, int be, int k) {
        int m = ae - as;
        int n = be - bs;
        if (m == 0) {
            return b[bs + k - 1];
        } else if (n == 0) {
            return a[as + k - 1];
        }
        if (m == 1 && n == 1) {
            if (k == 1) {
                return Math.min(a[as], b[bs]);
            } else {
                return Math.max(a[as], b[bs]);
            }
        }
        if (k == m + n) {
            return Math.max(a[ae - 1], b[be - 1]);
        }
        float ratio = k * m * 1f / (m + n);
        int i = Math.round(m * ratio);
        i = fit(i, 0, m - 1);
        i = fit(i, k - 1 - n, k - 1);
        int j = k - 1 - i;
        if (a[as + i] > b[bs + j]) {
            return findKth(a, as, as + i, b, bs + j, be, k - j);
        } else {
            return findKth(a, as + i, ae, b, bs, bs + j, k - i);
        }
    }

    private int fit(int a, int lower, int upper) {
        if (a < lower)
            return lower;
        else if (a > upper)
            return upper;
        else
            return a;
    }
}
