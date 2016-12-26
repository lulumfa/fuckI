// reference: http://blog.csdn.net/linhuanmars/article/details/24826871
//O(n2), space O(solution = Cn3) = O(n3)
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums == null || nums.length <3) return res;
        
        Arrays.sort(nums);
        for(int i = nums.length -1; i >=2; i--) {
            if(i == nums.length -1 || nums[i] != nums[i+1]) {
                List<List<Integer>> subsets = twoSum(nums, i -1, -nums[i]);
                for(List<Integer> list : subsets) {
                    list.add(nums[i]);
                    res.add(list);
                }
            }
        }
        
        return res;
    }
    
    private List<List<Integer>> twoSum(int[] nums, int end, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int left = 0, right = end;
        
        while(left < right) {
            int sum = nums[left] + nums[right];
            if(sum == target) {
                List<Integer> list = new ArrayList<Integer>();
                list.add(nums[left]);
                list.add(nums[right]);
                res.add(list);
                left++;
                right--;
                while(left < right && nums[left] == nums[left -1]) left++;
                while(left < right && nums[right] == nums[right +1]) right--;
            } else if(sum < target) {
                left++;
            } else {
                right--;
            }
        }
        
        return res;
    }
}
