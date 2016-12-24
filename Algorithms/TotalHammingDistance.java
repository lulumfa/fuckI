// O(n), space O(1)
// count every bits since these are things actually matter to hamming distance
//It's O(N*logV) where V is the upper bound of numbers in the given input.

public class Solution {
    public int totalHammingDistance(int[] nums) {
        if(nums == null || nums.length < 2) return 0;
        
        int total = 0, n = nums.length;
        
        for(int i = 0; i < 32; i++) {
            int bitCount = 0;
            
            for(int j = 0; j < n; j++) {
                bitCount += (nums[j] >>> i) & 1;
            }
            total += bitCount * (n - bitCount);
        }
        
        return total;
    }
}

//O(n2), space O(1)
public class Solution {
    public int totalHammingDistance(int[] nums) {
        if(nums == null || nums.length < 2) return 0;
        
        int total = 0;
        for(int i = 0; i  < nums.length -1; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                total += calHammingDistance(nums[i], nums[j]);
            }
        }
        
        return total;
    }
    
    private int calHammingDistance(int x, int y) {
        int xor = x ^ y;
        int count = 0;
        while(xor != 0) {
            count++;
            xor &= xor -1;
        }
        
        return count;
    }
}
