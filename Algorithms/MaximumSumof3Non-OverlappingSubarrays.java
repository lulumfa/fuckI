// O(n), space O(n) n is the len of the input list
// https://github.com/YaokaiYang-assaultmaster/LeetCode/blob/master/LeetcodeAlgorithmQuestions/689.%20Maximum%20Sum%20of%203%20Non-Overlapping%20Subarrays.md
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        int[] left = new int[n];
        int[] right = new int[n];
        int[] ret = new int[3];
        
        // First get the prefix sum of nums.
        // Prefix sum enables us to get the sum of k consecutive element in O(1) time
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        
        // DP for the left intetval max sum
        for (int i = k, tot = sum[k] - sum[0]; i < n; i++) {
            if (sum[i + 1] - sum[i - k + 1] > tot) {
                tot = sum[i + 1] - sum[i - k + 1];
                left[i] = i - k + 1;
            } else {
                left[i] = left[i - 1];
            }
        }
        
        // DP for the right interval max sum
        right[n - k] = n - k;
        for (int i = n - 1 - k, tot = sum[n] - sum[n - k]; i >= 0; i--) {
            if (sum[i + k] - sum[i] >= tot) {
                tot = sum[i + k] - sum[i];
                right[i] = i;
            } else {
                right[i] = right[i + 1];
            }
        }
        
        // Find the max sum by iterating through the middle interval index based on above 2 cache.
        int maxSum = 0;
        for (int i = k; i <= n - 2 * k; i++) {
            int l = left[i - 1], r = right[i + k];
            int tot = sum[l + k] - sum[l] + sum[r + k] - sum[r] + sum[i + k] - sum[i];
            if (tot > maxSum) {
                ret[0] = l;
                ret[1] = i;
                ret[2] = r;
                maxSum = tot;
            }
        }
        
        return ret;
    }
}

// O(N) = O(N*n) N is the lenght of the array, n is nunber of intervals, k = interval size
// https://github.com/YaokaiYang-assaultmaster/LeetCode/blob/master/GeneralizedMethod/Maximum%20Sum%20of%20n%20Non-Overlapping%20Subarrays.md
// can use the generalized method below for any # of intervals n

class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        return maxSumOfThreeSubarraysHelp(nums, k, 3);
    }
    
        public int[] maxSumOfThreeSubarraysHelp(int[] nums, int k, int n) {
        int[][] dp = new int[n + 1][nums.length + 1];
        int[][] index = new int[n + 1][nums.length + 1];
        int tot = 0;
        // prefix sum
        int[] sum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = nums[i] + sum[i];
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = k - 1; j < nums.length; j++) {
                int tmpMax = sum[j + 1] - sum[j - k + 1] + dp[i - 1][j - k + 1];
                if (tmpMax > dp[i][j]) {
                    dp[i][j + 1] = tmpMax;
                    index[i][j + 1] = j - k + 1;
                } else {
                    dp[i][j + 1] = dp[i][j];
                    index[i][j + 1] = index[i][j];
                }
            }
        }
        
        int[] ret = new int[n];
        int prev = nums.length;
        for (int i = n; i > 0; i--) {
            ret[i - 1] = index[i][prev];
            prev = ret[i - 1];
        }
        
        return ret;
    }
}
