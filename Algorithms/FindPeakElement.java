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
