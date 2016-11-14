// O(n), spaceO(n)

public class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if(nums == null || nums.length == 0) return 0;
        int minLen = 0;
        
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        int prefixSum = 0;
        
        for(int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if(prefixSum == k) minLen = i + 1;
            if(map.containsKey(prefixSum - k)) {
                minLen = Math.max(minLen, i - map.get(prefixSum - k));
            }
            if(!map.containsKey(prefixSum)) map.put(prefixSum, i);
        }
        return minLen;
    }
}
