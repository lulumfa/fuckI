//reference: http://blog.csdn.net/linhuanmars/article/details/20089131

public class Solution {
    public int sqrt(int x) {
        if(x<0) return -1;
        int l = 1;
        int r = x;
        while(l<=r) {
            int mid = (l+r)/2;
            if(x/mid>=mid && x/(mid+1)<(mid+1)) return mid;
            if(x/mid<mid) r = mid-1;
            else l = mid +1;
        }
        return 0;
    }
}
