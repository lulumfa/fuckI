// O(n), space O(n)
class Solution {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        int sum = 0;
        int count = 0;
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int target = sum - k;
            if (map.containsKey(target)) {
                count += map.get(target);
            }
            
            if (!map.containsKey(sum)) {
                map.put(sum, 0);
            }
            map.put(sum, map.get(sum) + 1);
        }
        return count;
    }
}
