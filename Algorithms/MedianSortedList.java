// latest mine, O(log (A + B)), space O(log (A + B))

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1 == null && nums2 ==null || nums1.length ==0 && nums2.length ==0 ) return 0;
        if(nums1 == null || nums1.length ==0) return nums2.length %2 == 0 ? (nums2[nums2.length/2-1] + nums2[nums2.length/2])/2.0d : nums2[nums2.length/2];
        if(nums2 == null || nums2.length ==0) return nums1.length %2 == 0 ? (nums1[nums1.length/2-1] + nums1[nums1.length/2])/2.0d : nums1[nums1.length/2];
        int len = nums1.length + nums2.length;
        int k= len /2;
        return len%2 ==0 ? (findMedian(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, k) + findMedian(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, k+1))/2.0d : 
            findMedian(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, k+1);
    }
    
    private int findMedian(int[] nums1, int i1, int i2, int[] nums2, int j1, int j2, int k){

        int len1 = i2 - i1 + 1;
        int len2 = j2 - j1 + 1;
        if(len1 > len2) return findMedian(nums2, j1, j2, nums1, i1, i2, k);

        if(len1 <= 0) return nums2[j1 + k -1];
        if(k==1) return Math.min(nums1[i1], nums2[j1]);

        int pos1 = Math.min(len1, k/2);
        int pos2 = k- pos1;
        int val1 = nums1[i1 + pos1 -1];
        int val2 = nums2[j1 + pos2 -1];
        if(val1 == val2) return val1;
        else if(val1 < val2){
            return this.findMedian(nums1, i1 + pos1, i2, nums2, j1, j1 + pos2-1, k-pos1);
        }else {
            return this.findMedian(nums1, i1, i1 + pos1 -1, nums2, j1 + pos2, j2, k-pos2);
        }
    }

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
