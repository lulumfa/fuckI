public class Solution {
    public int maxProfit(int[] prices) {
        if(prices==null || prices.length<=1) return 0;
        int max = 0;
        int sum = 0;
        for(int i=1; i< prices.length;i++) {
            sum  = Math.max(0, sum + prices[i] - prices[i-1]);
            max = Math.max(max, sum);
        }
        return max;
    }
}
