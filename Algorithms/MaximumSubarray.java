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
