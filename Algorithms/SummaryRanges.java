// O(n)

// my latest

public class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<String>();
        if(nums == null || nums.length ==0) return res;
        
        int start = 0;
        int i = 0;
        for(; i < nums.length; i++) {
            if(i > 0 && (nums[i] -1) > (nums[i-1])) {
                res.add(createRange(nums[start], nums[i-1]));
                start = i;
            }
        }
        res.add(createRange(nums[start], nums[i-1]));
        return res;
    }
    
    private String createRange(int start, int end) {
        if(start > end) return "";
        if(start == end) return String.valueOf(start);
        else return start + "->" + end;
    }
}


public class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<String>();
        if(nums == null || nums.length ==0) return list;
        
        int next = nums[0];
        int pre = nums[0];
        for(int i = 1; i< nums.length; i++) {
            if(nums[i] != (pre +1)) {
                list.add(createRange(next, pre));
                next  = nums[i];
                pre = nums[i];
            } else {
                pre = nums[i];
            }
        }
        list.add(createRange(next, pre));
        return list;
    }
    
    private String createRange(int start, int end) {
        if(start == end) return String.valueOf(start);
        return String.valueOf(start) + "->" + String.valueOf(end);
    }
}
