package com.dp;

import junit.framework.Assert;

//Given a list of N coins, their values (V1, V2, ... , VN), 
//and the total sum S. Find the minimum number of coins the 
//sum of which is S (we can use as many coins of one type as we want), 
//or report that it's not possible to select coins in such a way that they sum up to S. 

//This is a NP hard problem. Can be solved by using Dynamic Programming.
//See this website for explanation. http://community.topcoder.com/tc?module=Static&d1=tutorials&d2=dynProg 

public class MinCoinProblem {
	
	public static int FindMinCoin(int[] coins, int sum){
		int status[] = new int[sum+1];
		
		for(int i=1;i<=sum; i++)
		{
			status[i] = Integer.MAX_VALUE;
			for(int j=0; j<coins.length; j++){
				if(coins[j]<=i && status[i-coins[j]] != Integer.MAX_VALUE && status[i-coins[j]] + 1 < status[i]){
					status[i] = status[i-coins[j]] + 1;
				}
			}
		}
		
		return status[sum] == Integer.MAX_VALUE ? -1 :status[sum];
	
	}
	
	public static void main(String[] args){
		int[] coins = {3,5};
		Assert.assertEquals(2, MinCoinProblem.FindMinCoin(coins, 10));
		Assert.assertEquals(-1, MinCoinProblem.FindMinCoin(coins, 4));
		Assert.assertEquals(3, MinCoinProblem.FindMinCoin(coins, 13));
		int[] coins1 = {11,5,7};
		Assert.assertEquals(-1, MinCoinProblem.FindMinCoin(coins1, 13));
		Assert.assertEquals(3, MinCoinProblem.FindMinCoin(coins1, 27));
		Assert.assertEquals(5, MinCoinProblem.FindMinCoin(coins1, 35));
		
	}
	
	//Related Question: output the coin list that sum up to S, do not care about the # of coins this time
	//Explanation: After understanding the algorithm above, it is easier to understand the algorithm 
	//below. However, it is a little bit different since it only record each coin using to form the solution.
	
	//Be careful, i am not so sure about this answer!!! Reference: http://stackoverflow.com/questions/4247662/the-minimum-number-of-coins-the-sum-of-which-is-s
	//Contributor: Ziyi
	public static List<Integer> getCoinSet(int S, int[] coins) {
	    List<Integer> coinsSet = new LinkedList<Integer>();
	    if (S <= 0) return coinsSet;

	    int[] coinSumArr = buildCoinstArr(S, coins);

	    if (coinSumArr[S] < 0) throw new RuntimeException("Not possible to get given sum: " + S);

	    int i = S;
	    //retrieve the coins used to form the solution
	    while (i > 0) {
	        int coin = coins[coinSumArr[i]];
	        coinsSet.add(coin);
	        i -= coin;
	    }

	    return coinsSet;
	}

	public static int[] buildCoinstArr(int S, int[] coins) {
	    Arrays.sort(coins);
	    int[] result = new int[S + 1];

	    for (int s = 1; s <= S; s++) {
	        result[s] = -1;
	        //Different from the previous one, using descending sequence, and can avoid excess calculation
	        for (int i = coins.length - 1; i >= 0; i--) {
	            int coin = coins[i];
	            if (coin <= s && result[s - coin] >= 0) {
	                result[s] = i;
	                // Since smaller i cannot generate few # of coins used to form the solution
	                //So after getting one possible i, we can just break the loop
	                break;
	            }
	        }
	    }

	    return result;
	}
}
