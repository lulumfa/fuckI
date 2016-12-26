// O(2^n) exponential, space same, equals to the solutions set
// with dup

// recursively

public class Solution {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        if(nums == null) return res;
        Arrays.sort(nums);
        dfsFindSubset(res, nums, 0, new ArrayList<Integer>());
        
        return res;
    }
    
    private void dfsFindSubset(List<List<Integer>> res, int[] nums, int start, List<Integer> list) {
        res.add(new ArrayList<Integer>(list));
        if(start == nums.length) {
            return;
        }
        for(int i = start; i < nums.length; i++) {
            if(i == start || nums[i] != nums[i-1]) {
                list.add(nums[i]);
                dfsFindSubset(res, nums, i + 1, list);
                list.remove(list.size() -1);
            }
        }
    }
}

//iteratively

public class Solution {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        if(nums == null) return res;
        Arrays.sort(nums);
        res.add(new ArrayList<Integer>());
        
        int start = 0;
        for(int i = 0; i < nums.length; i++) {
            int len = res.size();
            for(int j = start; j < len; j++) {
                List<Integer> list = new ArrayList<Integer>(res.get(j));
                list.add(nums[i]);
                res.add(list);
                
                if(i + 1 < nums.length && nums[i + 1] == nums[i]) {
                    start = len;
                } else {
                    start = 0;
                }
            }
        }
        
        return res;
    }

}
