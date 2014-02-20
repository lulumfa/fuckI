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
