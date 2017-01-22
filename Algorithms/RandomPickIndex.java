//O(n), space O(1)

public class Solution {

    Random rand;
    int[] nums;
    // when do such number, 0, first time, 1/1 = 1, for kth target number, assume 1/k, for (k+1)th, 
    // if remains the same one or say anyone else, 1/k * k/k+1 = 1/k+1, if the current one, 1/k+1
    public Solution(int[] nums) {
        this.rand = new Random();
        this.nums = nums;
    }
    
    public int pick(int target) {
        int res = -1;
        int count = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == target && rand.nextInt(++count) == 0) {
                res = i;
            }
        }
        
        return res;
    }
}

public class Solution {
    
    private int[] nums;
    private Random rand;
    
    public Solution(int[] nums) {
        this.nums = nums;
        rand = new Random();
    }
    
    public int pick(int target) {
        int res = -1;
        int count = 0;
        
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == target) {
                if(rand.nextInt(++count) == 0) res = i;
            }
        }
        return res;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
