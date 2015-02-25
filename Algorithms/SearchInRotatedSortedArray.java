// reference: http://blog.csdn.net/linhuanmars/article/details/20525681

// 这里是这样的，因为mid有可能会等于left所以有可能会跳过第一个判断，所以如果要把left放前面，那就需要用>=就可以了哈～ if(A[l] <= A[m]) {
//每次我们都可以切掉一半的数据，所以算法的时间复杂度是O(logn)，空间复杂度是O(1)

public class Solution {
    public int search(int[] A, int target) {
        if(A==null || A.length==0) return -1;
        int l = 0;
        int r = A.length-1;
        
        while(l<=r) {
            int m = (r+l)/2;
            if(target == A[m]) return m;
            
            if(A[l] <= A[m]) {
                if(target>=A[l] && target < A[m]) r = m-1;
                else l = m+1;
            } else {
                if(target>A[m] && target <= A[r]) l = m+1;
                else r = m-1;
            }
        }
        return -1;
        
    }
}
