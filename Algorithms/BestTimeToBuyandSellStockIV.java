
// http://blog.csdn.net/linhuanmars/article/details/23236995

//this one actually refered the below one
// http://bookshadow.com/weblog/2015/02/18/leetcode-best-time-to-buy-and-sell-stock-iv/

public class Solution {
    public int maxProfit(int k, int[] prices) {
             if(prices==null || prices.length==0)
        return 0;
    if(k>prices.length/2) return quick(prices);
    int[] local = new int[k+1];
    int[] global = new int[k+1];
    for(int i=0;i<prices.length-1;i++)
    {
        int diff = prices[i+1]-prices[i];
        for(int j=k;j>=1;j--)
        {
            local[j] = Math.max(global[j-1]+(diff>0?diff:0), local[j]+diff);
            global[j] = Math.max(local[j],global[j]);
        }
    }
    return global[k];
    }
    
    public int quick(int[] prices) {
        if(prices==null || prices.length<=1) return 0;
        int sum = 0;
        for(int i=1; i< prices.length; i++) {
            sum+= Math.max(prices[i]- prices[i-1], 0);
        }
        return sum;
    }
}
