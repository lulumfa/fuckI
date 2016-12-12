// O(n), space O(1)

public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        // sliding windows
        if(nums == null || s <= 0) return 0;
        int minLen = Integer.MAX_VALUE, left = 0, right = 0, sum = 0;
        
        for(; right < nums.length; right++) {
            sum += nums[right];
            while(left <= right && sum >= s) {
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left++];
            }
        }
        return minLen != Integer.MAX_VALUE ? minLen : 0;
    }
}
