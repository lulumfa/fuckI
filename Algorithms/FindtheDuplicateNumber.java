// less than n2, O(nlog) space constant

// or use bitset, boolean[] , O(n), space O(n)

public class Solution {
    public int findDuplicate(int[] nums) {
        if(nums == null || nums.length < 2) return 0;
        
        int left = 1;
        int right = nums.length -1;
        
        while(left< right) {
            int mid = (left + right)/2;
            int count = 0;
            for(int i = 0; i< nums.length; i++) {
                if(nums[i] <= mid) count++;
            }
            if(count > mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
