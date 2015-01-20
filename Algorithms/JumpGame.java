//reference: http://blog.csdn.net/linhuanmars/article/details/21354751

// 因为只需要一次遍历时间复杂度是O(n)，而空间上是O(1)

public class Solution {
    public boolean canJump(int[] A) {
        if(A==null || A.length==0) return false;
        
        int reach  = 0;
        for(int i = 0; i<A.length && i<=reach; i++) {
            if(i+A[i]>reach) {
                reach = i+A[i];
            }
        }
        return reach>=(A.length-1);
    }
}
