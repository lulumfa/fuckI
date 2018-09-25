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

// II , O(n), break the circle and check the only cases
class Solution {
    public int rob(int[] nums) {
        if (nums == null) return 0;
        if (nums.length == 1) return nums[0];
        return Math.max(robRange(nums, 0, nums.length -2), robRange(nums, 1, nums.length -1));
    }
    
    public int robRange(int[] nums, int start, int end) {
        if (nums == null || start > end || start < 0 || end >= nums.length) return 0;
        
        int prePre = 0, pre = 0;
        for (int i = start; i <= end; i++) {
            int cur = Math.max(prePre + nums[i], pre);
            prePre = pre;
            pre = cur;
        }
        return pre;
    }
}

// III, O(n)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int rob(TreeNode root) {
        int[] res = robHelper(root);
        return Math.max(res[0], res[1]);
    }
    
    private int[] robHelper(TreeNode node) {
        int[] cur = new int[2]; // 1st element points to res exluding the current value, 2nd includes it
        if (node == null) return cur;
        int[] left = robHelper(node.left);
        int[] right = robHelper(node.right);
        
        cur[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        cur[1] = node.val + left[0] + right[0];
        return cur;
    }
}
