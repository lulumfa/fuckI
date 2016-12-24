// O(kn), O(1), only need preMin, preSecMin, preMinColor

public class Solution {
    public int minCostII(int[][] costs) {
        if(costs == null || costs.length == 0 || costs[0].length == 0) return 0;
        if(costs[0].length == 1 && costs.length > 1) return 0;
        
        int preMin = 0, preSecMin = 0, preMinColor = -1;
        
        for(int i = 0; i < costs.length; i++) {
            int curMin = Integer.MAX_VALUE, curSecMin = Integer.MAX_VALUE, curMinColor = -1;
            for(int j = 0; j < costs[0].length; j++) {
                int cost = (j == preMinColor ? preSecMin : preMin) + costs[i][j]; 
                if(cost < curMin) {
                    curSecMin = curMin;
                    curMin = cost;
                    curMinColor = j;
                } else if(cost < curSecMin) {
                    curSecMin = cost;
                }
            }
            preMin = curMin;
            preSecMin = curSecMin;
            preMinColor = curMinColor;
        }
        
        return preMinColor == -1 ? 0 : preMin;
    }
} 
