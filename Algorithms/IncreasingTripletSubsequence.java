// O(n), space O(1)

public class Solution {
    public boolean increasingTriplet(int[] nums) {
        if(nums == null || nums.length < 3) return false;
        
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        
        // 3, 4, 1, 5 also covered, though 3 was replaced 
        for(Integer num : nums) {
            if(num <= first) {
                first = num;
            } else if(num <= second) {
                second = num;
            } else {
                return true;
            }
        }
        return false;
    }
}
