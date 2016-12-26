// binary search, O(lgn), use mid = left + (right -left)/2 to avoid overflow

/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        if(n<1) return 0;
        
        int left = 1, right = n;
        while(left <= right) {
            int mid = left + (right - left)/2;
            if(isBadVersion(mid)) {
                right = mid -1;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    } 
}
