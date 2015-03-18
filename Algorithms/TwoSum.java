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
