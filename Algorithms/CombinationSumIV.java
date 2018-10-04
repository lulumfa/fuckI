//dp O(mn), space O(n), if negative number allowed, we need to add requirement that no dup usage aloowe, or infinite loop -1 + 1

// if nums size small, then sort first
public class Solution {
    public int combinationSum4(int[] nums, int target) {
        if(nums == null || target <=0) return 0;
        int[] dp = new int[target+1];
        dp[0] = 1;
        Arrays.sort(nums);
        for(int i = 1; i<= target; i++) {
            for(int j = 0; j < nums.length && nums[j] <=i; j++) {
                if(nums[j] <= i) {
                    dp[i] += dp[i-nums[j]];
                }
            }
        }
        return dp[target];
    }
}

// normal one

public class Solution {
    public int combinationSum4(int[] nums, int target) {
        if(nums == null || target <=0) return 0;
        int[] dp = new int[target+1];
        dp[0] = 1;
        
        for(int i = 1; i<= target; i++) {
            for(int j = 0; j < nums.length; j++) {
                if(nums[j] <= i) {
                    dp[i] += dp[i-nums[j]];
                }
            }
        }
        return dp[target];
    }
}
