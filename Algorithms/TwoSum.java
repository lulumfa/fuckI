// latest 

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        if(nums == null || nums.length <2) return null;
        
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for(int i = 0; i< nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                res[0] = map.get(target - nums[i]);
                res[1] = i;
                return res;
            } else {
                map.put(nums[i], i);
            }
        }
        return res;
    }
}

public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] res = {0, 0};
        if(numbers==null || numbers.length<=1) return res;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i<numbers.length; i++) {
            if(map.get(target-numbers[i])!=null) {
                int small = Math.min(i, map.get(target-numbers[i]));
                int large = Math.max(i, map.get(target-numbers[i]));
                res[0] = small +1;
                res[1] = large +1;
            } else {
                map.put(numbers[i], i);
            }
        }
        return res;
    }
}
