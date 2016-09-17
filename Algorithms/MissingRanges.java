// O(n), 

public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<String>();
        if(lower> upper || nums == null) return res;
        
        int i =0;
        while(i < nums.length && nums[i] < lower) i++;
        int next = lower;
        
        for(; i< nums.length; i++) {
            if(nums[i] == next) {
                next = nums[i]+1;
                continue;
            }
            if(nums[i] >next) {
                res.add(formRange(next, nums[i]-1));
                next = nums[i] +1;
            }
        }
        if(upper >= next) {
            res.add(formRange(next, upper));
        }
        return res;
    }
    
    private String formRange(int start, int end) {
        if(start == end) return String.valueOf(start);
        return String.valueOf(start) + "->" + String.valueOf(end);
    }
}
