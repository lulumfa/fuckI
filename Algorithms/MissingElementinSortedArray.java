// O(logn) space O(1)

class Solution {
    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        if(nums[n - 1] - nums[0] - (n - 1 - 0) < k){
            return nums[n - 1] + k - missCount(nums, n - 1);
        }
        
        int l = 0;
        int r = n - 1;
        
        while(l < r){
            int mid = l + (r - l) / 2;
            if(missCount(nums, mid) < k){
                l = mid + 1;
            }else{
                r = mid;
            }
        }
        
        return nums[l - 1] + k - missCount(nums, l - 1);
    }
    
    private int missCount(int [] nums, int mid){
        return nums[mid] - nums[0] - mid;
    }
}
