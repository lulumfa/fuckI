public class Solution {
    public int maxProfit(int[] prices) {
        if(prices==null || prices.length<=1) return 0;
        int sum = 0;
        for(int i=1; i< prices.length; i++) {
            sum+= Math.max(prices[i]- prices[i-1], 0);
        }
        return sum;
    }
}
