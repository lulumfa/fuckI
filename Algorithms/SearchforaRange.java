//faster considering in the failure scenario this only go through it once
//lgn, constant space
public class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null) return null;
        
        int[] res = {-1, -1};
        
        // search left bound;
        int left = 0;
        int right = nums.length -1;
        while(left <= right) {
            int mid = (left + right)/2;
            if(nums[mid] >= target) right = mid -1;
            else left = mid + 1;
        }
        if((right + 1) >= nums.length || nums[right + 1] != target) return res;
        res[0] = right+1;
        
        // search right bound;
        left = 0;
        right = nums.length -1;
        while(left <= right) {
            int mid = (left + right)/2;
            if(nums[mid] > target) right = mid -1;
            else left = mid + 1;
        }
        res[1] = left-1;
        return res;
    }
}

// http://blog.csdn.net/linhuanmars/article/details/20593391

public int[] searchRange(int[] A, int target) {
    int[] res = {-1,-1};
    if(A==null || A.length==0)
    {
        return res;
    }
    int ll = 0;
    int lr = A.length-1;
    while(ll<=lr)
    {
        int m = (ll+lr)/2;
        if(A[m]<target)
        {
            ll = m+1;
        }
        else
        {
            lr = m-1;
        }
    }
    int rl = 0;
    int rr = A.length-1;
    while(rl<=rr)
    {
        int m = (rl+rr)/2;
        if(A[m]<=target)
        {
            rl = m+1;
        }
        else
        {
            rr = m-1;
        }
    }
    if(ll<=rr)
    {
        res[0] = ll;
        res[1] = rr;
    }
    return res;
}
