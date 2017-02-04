// binary search plus validation, O(lg(max-min) * n), space constant

public class Solution {
    public int splitArray(int[] nums, int m) {
        if(nums == null || m < 1 || nums.length < m) return 0;
        
        int sum = 0, max = 0;
        for(Integer num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        
        int left = max, right = sum;
        
        while(left <= right) {
            int mid = left + (right - left)/2;
            if(isValid(nums, mid, m)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    private boolean isValid(int[] nums, int maxSum, int maxSegs) {
        int sum = 0, segs = 1;
        
        for(Integer num : nums) {
            sum += num;
            if(sum > maxSum) {
                sum = num;
                segs++;
                
                if(segs > maxSegs) return false;
            }
        }
        
        return true;
    }
}
