//reference: http://blog.csdn.net/linhuanmars/article/details/21356187

//时间复杂度仍然是O(n)，空间复杂度也是O(1)

public class Solution {
    public int jump(int[] A) {
        if(A==null) return -1;
        int reach = 0;
        int lastReach = 0;
        int step = 0;
        
        for(int i = 0; i<=reach && lastReach<A.length-1; i++) {
            if(i>lastReach) {
                step++;
                lastReach = reach;
            }
            reach = Math.max(reach, A[i] + i);
        }
        return lastReach>=(A.length-1) ? step : -1;
    }
}
