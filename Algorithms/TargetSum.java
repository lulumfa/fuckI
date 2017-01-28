// dp, O(sum * n), space O(sum)

public class Solution {
    
    //dp[i + 1][k + nums[i] * sgn] += dp[i][k]
    public int findTargetSumWays(int[] nums, int S) {
        if(nums == null || nums.length == 0) return 0;
        
        int sum = 0;
        for(Integer num : nums) sum += num;
        if(S< - sum || S > sum) return 0;
        
        int size = sum* 2 + 1;
        int[] ways = new int[size];
        ways[0 + sum] = 1;
        
        for(int i = 0; i < nums.length; i++) {
            int[] curWays = new int[size];
            
            for(int j = 0; j < size; j++) {
                if(ways[j] != 0) {
                    curWays[j + nums[i]] += ways[j];
                    curWays[j - nums[i]] += ways[j];
                }
            }
            
            ways = curWays;
        }
        
        return ways[sum + S];
    }
}

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
