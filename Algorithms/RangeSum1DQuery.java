// constructor O(n), sumRange O(1)
class NumArray {

    private int[] prefixSum;
    
    public NumArray(int[] nums) {
        prefixSum = new int[nums.length + 1];
        
        for (int i = 1; i <= nums.length; i++) {
            prefixSum[i] = nums[i -1] + prefixSum[i-1];
        }
    }
    
    public int sumRange(int i, int j) {
        return prefixSum[j+1] - prefixSum[i];
    }
}
