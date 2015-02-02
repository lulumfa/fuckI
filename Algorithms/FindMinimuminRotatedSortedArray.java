//reference: http://blog.csdn.net/linhuanmars/article/details/40449295

public class Solution {
    public int findMin(int[] num) {
        if(num==null || num.length==0) return Integer.MAX_VALUE;
        int l = 0; 
        int r = num.length-1;
        int min = num[0];
        while(l<=r) {
            int mid = (l+r)/2;
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
