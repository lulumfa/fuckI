// O(2^n), space(n)

public class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        if(nums == null || nums.length == 0) return 0;
        int[] ways = {0};
        dfsFindTargetSum(nums, 0, 0, S, ways);
        
        return ways[0];
    }
    
    private void dfsFindTargetSum(int[] nums, int start, int sum, int S, int[] ways) {
        if(start == nums.length) {
            if(sum == S) ways[0]++;
            return;
        }    
        
        dfsFindTargetSum(nums, start + 1, sum + nums[start], S, ways);
        dfsFindTargetSum(nums, start + 1, sum - nums[start], S, ways);
    }
}
