// O(nlg32?) ~ O(n)

package Airbnb;

public class MedianNumberInLargeFile {
	
	public static void main(String[] args) {
		MedianNumberInLargeFile m = new MedianNumberInLargeFile();
		System.out.println(m.findMedian(new int[] {8, 7, 3, 2, 1, 4, 5, 6}));
	}

	public double findMedian(int[] input) {
		if (input == null) return Integer.MAX_VALUE;
		int len = 0;
		for (Integer num : input) {
			len++;
		}
		return ((len & 1) == 1 
			? helper(input, Integer.MIN_VALUE, Integer.MAX_VALUE, len/2 + 1) 
			: (double)(helper(input, Integer.MIN_VALUE, Integer.MAX_VALUE, len/2) 
				   + helper(input, Integer.MIN_VALUE, Integer.MAX_VALUE, len/2 + 1)) /2);  
	}
	
	private long helper(int[] input, long low, long high, int k) {
		if (low >= high) return low;
		long pivot = low + (high - low)/2;
		
		int count = 0;
		long res = low;
		for (Integer num : input) {
			if (num <= pivot) {
				count++;
				res = Math.max(res, num);
			}
		}
		if (count == k) return pivot;
		else if (count < k) return helper(input, pivot + 1, high, k);
		else return helper(input, low, res, k);
	}
}
