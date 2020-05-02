//https://discuss.leetcode.com/topic/3191/o-n-bfs-solution

// continue as BFS, layer by layer, to next level only after processing everything at the current layer
public class Solution {
    public int jump(int[] nums) {
        if(nums == null || nums.length < 2) return 0;
        
        int nextReach = 0, curReach = 0, i = 0, level = 0;
        
        while(curReach - i + 1 > 0) {
            level++;
            
            while(i <= curReach) {
                nextReach = Math.max(nextReach, i + nums[i]);
                if(nextReach >= (nums.length -1)) return level;
                i++;
            }
            
            curReach = nextReach;
        }
        
        return -1;
    }
}

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
