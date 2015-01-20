//reference: http://blog.csdn.net/linhuanmars/article/details/21314059

// 时间上只需要扫描一次数组，所以时间复杂度是O(n)。所以空间复杂度是两个变量（local和global），即O(2)=O(1)。

public class Solution {
    public int maxSubArray(int[] A) {
        if(A==null || A.length==0) return Integer.MIN_VALUE;
        
        int local = A[0];
        int global = A[0];
        
        for(int i = 1; i <A.length; i++) {
            local = Math.max(local+A[i], A[i]);
            global = Math.max(global, local);
        }
        return global;
    }
}

// 并发现，这道题有两种经典解法，一个是：Kadane算法，算法复杂度O(n)；另外一个是分治法：算法复杂度为O(nlogn)。

public class Solution {
  
    public int maxSum(int[] a, int left, int right){
        if(left == right) return a[left];
        int mid = (left + right) / 2;
        int leftSum = maxSum(a, left, mid);
        int rightSum = maxSum(a, mid+1, right);
        
        int tmpLeftSum = 0;
        int tmpRightSum = 0;
        int s1 = Integer.MIN_VALUE, s2 = Integer.MIN_VALUE;
        for(int i = mid; i >= left; i--){
            tmpLeftSum += a[i];
            s1 = Math.max(s1, tmpLeftSum); 
        }
        for(int i = mid+1; i <= right; i++){
            tmpRightSum += a[i];
            s2 = Math.max(s2, tmpRightSum);
        }
        
        return Math.max(Math.max(leftSum, rightSum), s1+s2);
    }
    
    public int maxSum2(int[] A){
        int len = A.length;
        int ans = A[0];
        int tmpSum = A[0];
        for(int i = 1; i < len; i++){
            if(tmpSum > 0){
                tmpSum = tmpSum + A[i];
            }
            else tmpSum = A[i];
            ans = Math.max(ans, tmpSum);
        }
        return ans;
    }
    
    public int maxSubArray(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
        //return maxSum(A, 0, A.length-1);
        return maxSum2(A);
    }

}
