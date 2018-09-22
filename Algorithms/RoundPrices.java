// O(nlogn) space O(n)
// greedy, sort and compensate the most significant diff

package Airbnb;

import java.util.Arrays;
import java.util.Comparator;

public class RoundPrices {
	
	public static void main(String[] args) {
		double[] prices = new double[] {1.2, 2.3, 3.4};
		System.out.println(Arrays.toString(RoundPrices.roundPrices(prices)));
	}
	
	public static int[] roundPrices(double[] prices) {
		if (prices == null) return null;
		int n = prices.length;
		int[] res = new int[n];
		PriceWrapper[] pw = new PriceWrapper[n];
		
		int floorSum = 0;
		double sum = 0.0;
		
		for (int i = 0; i < n; i++) {
			sum += prices[i];
			int floor = (int) prices[i];
			floorSum += floor;
			int ceil = prices[i] > floor ? floor + 1 : floor;
			pw[i] = new PriceWrapper(ceil, ceil - prices[i]);
		}
		
		int diff = (int) Math.round(sum) - floorSum;
		
		Arrays.sort(pw, new Comparator<PriceWrapper>() {
			@Override
			public int compare(PriceWrapper a, PriceWrapper b) {
				if (a.diff <= b.diff) return -1;
				return 1;
//				return Double.compare(a.diff, b.diff);
			}
		});
		
		for (int i = 0; i < diff; i++) {
			res[i] = pw[i].ceilPrice;
		}
		
		for (int i = diff; i < n; i++) {
			res[i] = pw[i].ceilPrice - 1;
		}
		
		return res;
	}
}

class PriceWrapper {
	int ceilPrice; // floor price
	double diff; // diff from the real double to the ceil
	
	public PriceWrapper(int ceilPrice, double diff) {
		this.ceilPrice = ceilPrice;
		this.diff = diff;
	}
}
