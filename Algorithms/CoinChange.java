//np, O(kn) k = coins.length
// the sequence of coins does not matter, it is actually able to calculate all the combinations.
public class Solution {
    public int coinChange(int[] coins, int amount) {
        if(coins == null || amount <1) return 0;
        
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(Integer coin: coins) {
            for(int i = coin; i<= amount; i++) {
                if(dp[i-coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i-coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}



package Leet;
 
import java.util.ArrayList;

// runtime Complexity: NP hard, O(n^k)  

public class CoinChange 
{
	public static void main(String[] args)
	{
		setUpCoin(new int[]{1, 5, 10, 25}, 78);
	}
	public static void setUpCoin(int[] coins, int sum)
	{
		int len = coins.length;
		ArrayList<Integer> count = new ArrayList<Integer>(1);
		count.add(0);
		outputCombinations(coins, sum, coins[len-1],"", count);
	}
	
	public static void outputCombinations(int[] coins, int sum, int n, String str, ArrayList<Integer> count )
	{
		if(sum==0) 
		{
			count.set(0, count.get(0)+1);
			System.out.println("Case " + count.get(0) + ": " + str);	
		}
		for(int coin: coins)
		{
			if(coin<=n)
			{
				if(coin<=sum)
				{
					outputCombinations(coins, sum-coin, coin, str+coin+ " ", count);
				}
			}
		}
	}
}
