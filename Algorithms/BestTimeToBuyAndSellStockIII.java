// reference: http://blog.csdn.net/linhuanmars/article/details/23236995

public class Solution {
    // global[i][j] = max(g[i-1][j], local[i][j])
    
    // local[i][j] = max(global[i-1][j-1] + max(diff, 0), local[i-1][j] + diff)
    public int maxProfit(int[] prices) {
        if(prices==null || prices.length<=1) return 0;
    
        int[] global = new int[3];
        int[] local = new int[3];
        int diff = 0;
        for(int i = 1; i<prices.length; i++) {
            diff = prices[i] - prices[i-1];
            for(int j =2; j>0; j--) {
                local[j] = Math.max(global[j-1] + Math.max(diff, 0), local[j] + diff); 
                global[j] = Math.max(global[j], local[j]);
            }
        }
        return global[2];
    }
}
