// O(nlogn), space O(n), space can be optimized to O(1), which is online

class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null) return;
        int len = nums.length;
        if (len <= 1) return;
        
        Arrays.sort(nums);
        for (int i = 0; i < len/2; i++) {
            int temp = nums[i];
            nums[i] = nums[len - 1 -i];
            nums[len - 1 -i] = temp;
        }
        
        int[] copy = Arrays.copyOf(nums, len);
        
        int j = 0;
        for (int i = 1; i < len; i += 2) {
            nums[i] = copy[j++];
        }
        for (int i = 0; i < len; i += 2) {
            nums[i] = copy[j++];
        }
        
    }
}
