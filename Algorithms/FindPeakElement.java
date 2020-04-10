// O(lgn) space O(1)
public class Solution {
    public int findPeakElement(int[] num) {
        if(num==null || num.length==0) return -1;
        int l = 0;
        int r = num.length-1;
        while(l<=r) {
            if(l==r) return l;
            int mid = (l+r)/2;
            if(num[mid]<num[mid+1]) {
                l = mid+1;
            } else {
                r = mid;
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
