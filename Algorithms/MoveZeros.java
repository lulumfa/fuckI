// O(n), in place

public class Solution {
    public void moveZeroes(int[] nums) {
       if(nums == null || nums.length == 0) return;
       
       int lastNoneZeroIndex = 0;
       for(int i = 0; i< nums.length; i++) {
           if(nums[i] != 0) {
               nums[lastNoneZeroIndex++] = nums[i];
           }
       }
       while(lastNoneZeroIndex < nums.length) {
           nums[lastNoneZeroIndex++] = 0;
       }
    }
}
