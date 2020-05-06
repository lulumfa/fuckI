class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int max = 1;
        int pre = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]) {
                max = Math.max(max, i - pre + 1);
            } else {
                pre = i;
            }
        }
        return max;
    }
}
