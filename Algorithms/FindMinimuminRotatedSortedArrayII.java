// focus on one side, this way minimum changes from the previous question, still O(n)
public class Solution {
    public boolean search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return false;
        
        int left = 0, right = nums.length -1;
        
        while(left <= right) {
            int mid = left + (right -left)/2;
            if(nums[mid] == target) return true;
            
            if(nums[left] > nums[mid]) {
                if(target > nums[mid] && target <= nums[right]) left = mid + 1;
                else right = mid -1;
            } else if(nums[mid] > nums[left]){
                if(target >= nums[left] && target < nums[mid]) right = mid - 1;
                else left = mid + 1;
            } else {
                left++;
            }
        }
        
        return false;
    }
}

//reference: http://blog.csdn.net/linhuanmars/article/details/40449299

public class Solution {
    public int findMin(int[] num) {
        if(num==null || num.length==0) return Integer.MAX_VALUE;
        int l = 0;
        int r = num.length-1;
        int min = num[0];
        
        while(l<=r) {
            int mid = (l+r)/2;
            while(l<mid && num[l]==num[mid]) l++;
            if(num[l]<=num[mid]) {
                min = Math.min(min, num[l]);
                l = mid+1;
            } else {
                min = Math.min(min, num[mid+1]);
                r = mid;
            }
            
        }
        return min;
    }
}
