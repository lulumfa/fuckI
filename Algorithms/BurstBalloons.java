// https://discuss.leetcode.com/topic/30746/share-some-analysis-and-explanations

// this is divide and conqure way and there is a dp way also in that page , O(n3) as dp 

public class Solution {
    public int maxCoins(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        
        int n = 1;
        int[] copy = new int[nums.length+2];
        for(Integer num : nums) if(num>0) copy[n++] = num;
        copy[0] = copy[n++] = 1;
        
        return dcMemo(copy, new int[n][n], 0, n-1);
    }
    
    private int dcMemo(int[] nums, int[][] memo, int left, int right) {
        if((left+1) == right) return 0;
        if(memo[left][right] > 0) return memo[left][right];
        int res = 0;
        for(int i = left+1; i< right; i++) {
            res = Math.max(res, nums[i] * nums[right] * nums[left] + dcMemo(nums, memo, left, i) + dcMemo(nums, memo, i, right));
        }
        memo[left][right] = res;
        return res;
    }
}
