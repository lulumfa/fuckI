//O(n) constant space, except the space for the res

public class Solution {
    public int[] productExceptSelf(int[] nums) {
        if(nums == null || nums.length <1 ) return null;
        int[] res = new int[nums.length];
        
        res[0] = 1;
        // left to right
        for(int i = 1; i < nums.length; i++) {
            res[i] = res[i-1] * nums[i-1];
        }
        
        // right to left
        int right = 1;
        for(int i = nums.length -1; i >=0; i--) {
            res[i] = res[i] * right;
            right *= nums[i];
        }
        return res;
    }
}
