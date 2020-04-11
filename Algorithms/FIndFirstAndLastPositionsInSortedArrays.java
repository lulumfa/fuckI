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
