package Leetcode;

import java.util.Arrays;
import java.util.HashMap;

public class SubRangeSum {

	
	public static void main(String[] args) {
		int[] input = {3, 4, 1, 3, 6, 1, 2};
		
		int target = 111;
		
		System.out.println(Arrays.toString(findRangeSumToTarget(input, target)));
	}
	
	public static int[] findRangeSumToTarget(int[] input, int target) {
		int[] res = {-1, -1};
		if(input == null || input.length == 0) return res;
		
		int preFix = 0;
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for(int i = 0; i < input.length; i++) {
			preFix += input[i];
			if(preFix == target) {
				return new int[]{0, i};
			}
			if(map.containsKey(preFix - target)) {
				return new int[]{map.get(preFix - target) + 1, i};
			}
			map.put(preFix, i);
		}
		return res;
	}
}
