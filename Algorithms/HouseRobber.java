// enhancement, just need 2 pointers actually instead of the array, space then will be constant
// I, new 
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int prePre = 0, pre = 0;
        for (Integer num : nums) {
            int cur = Math.max(prePre + num, pre);
            prePre = pre;
            pre = cur;
        }
        return pre;
    }
}
public class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        
        int a = 0, b = 0;
        
        for(Integer num : nums) {
            int temp = a + num;
            a = b;
            b = Math.max(b, temp);
        }
        return Math.max(a, b);
    }
}

// O(n), space(n)
//dp

public class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        
        int[] dp = new int[nums.length + 1];
        dp[1] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            dp[i+1] = Math.max(dp[i-1] + nums[i], dp[i]);
        }
        
        return dp[nums.length];
    }
}
