// one case from interview
// 找第一次和最后一次出现的长度而不是index但是有了index长度就出来了。
// 小哥说有些corner case比如全是要找的k怎么办其实可以在找到后判断start和end的值是否等于k来选择只找一边
// 但是我当时写的是typic way就是两边都找总时间复杂度没影响但是对于某些corner case会好一些

// logn, constant space
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums == null || nums.length == 0) return res;
        
        int leftIndex = findIndex(nums, target, false);
        if (leftIndex < 0 || leftIndex >= nums.length || nums[leftIndex] != target) return res;
        res[0] = leftIndex;
        res[1] = findIndex(nums, target, true);
        
        return res;
    }
    
    private int findIndex(int[] nums, int target, boolean rightMost) {
        if (nums == null) return -1;
        int left = 0, right = nums.length -1;
        while (left <= right) {
            int mid = left + (right -left) /2;
            if (nums[mid] < target || (nums[mid] == target && rightMost)) {
                left = mid + 1;
            } else {
                right = mid -1;
            }
        }
        return rightMost ? right : left;
    }
}
