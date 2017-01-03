// O(1), no extra space

public class Solution {
    int[] nums;
    public Solution(int[] nums) {
        this.nums = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return this.nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int n = this.nums.length;
        int[] shuffled = this.nums.clone();
        Random rand = new Random();
        for(int i = n - 1; i > 0; i--) {
            int r = rand.nextInt(i + 1);
            int temp = shuffled[i];
            shuffled[i] = shuffled[r];
            shuffled[r] = temp;
        }
        
        return shuffled;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
