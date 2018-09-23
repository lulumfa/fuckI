// O(n)

class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (i + 1 >= len) break;
            if (((i & 1) == 0 && nums[i] > nums[i+1]) || ((i & 1) == 1 && nums[i] < nums[i+1])) {
                int temp = nums[i];
                nums[i] = nums[i+1];
                nums[i+1] = temp;
            }
        }
    }
}
