// lgn, constant space, be careful of the long, integer overflow when doing the *

public class Solution {
    public boolean isPerfectSquare(int n) {
        if(n < 0) return false;
        long num = (long)n;
        long small = 0;
        long large = num;
        
        while(small <= large) {
            long mid = (small + large)/2;
            if(mid * mid == num) return true;
            else if (mid * mid < num) {
                small = mid + 1;
            } else {
                large = mid -1;
            }
        }
        return false;
    }
}
