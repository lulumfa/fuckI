// O(lgn) space O(1)
class Solution {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        
        int left = 0, right = nums.length -1;
        while (left <= right) {
            if (left == right) return left;
            int mid = left + (right -left) /2;
            
            if (mid + 1 < nums.length && nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return -1;
    }
}

// O(n), space 1

class Solution {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        
        int candidate = 0, i = 1;
        while (i < nums.length) {
            if (nums[candidate] < nums[i]) {
                candidate = i++;
            } else {
                return candidate;
            } 
        }
        return candidate;
    }
}
