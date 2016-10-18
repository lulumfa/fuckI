// output all combination, 1. usable, no dup. does not make sense to have dup 2. not reusable, no dup 3. not resuable, with dup 

package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum {
	public static void main(String[] args) {
//		int[] input = {1, 3, 5};
//		System.out.println(findAllCombinationSumResableNoDup(input, 5));
		
//		int[] input = {1, 2, 3, 5, 4};
//		System.out.println(findAllCombinationSumNonReusableNoDup(input, 5));
		
		int[] input = {1, 1, 2, 3, 5, 4};
		System.out.println(findAllCombinationSumNonReusableWithDup(input, 5));
	}
	
	public static List<List<Integer>> findAllCombinationSumResableNoDup(int[] input, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(input == null || input.length ==0) return res;
		dfsReusableNoDup(res, new ArrayList<Integer>(), input, target);
		return res;
	}
	
	private static void dfsReusableNoDup(List<List<Integer>> res, List<Integer> list, int[] input, int target) {
		if(target == 0) {
			res.add(new ArrayList<Integer>(list));
			return;
		}
		for(Integer num: input) {
			if(num <= target) {
				list.add(num);
				dfsReusableNoDup(res, list, input, target - num);
				list.remove(list.size()-1);
			}
		}
	}
	
	public static List<List<Integer>> findAllCombinationSumNonReusableNoDup(int[] input, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(input == null || input.length ==0) return res;
		HashSet<Integer> set = new HashSet<Integer>();
		for(Integer num : input) {
			set.add(num);
		}
		dfsNonReusableNoDup(res, new ArrayList<Integer>(), set, target);
		return res;
	}
	
	private static void dfsNonReusableNoDup(List<List<Integer>> res, List<Integer> list, Set<Integer> input, int target) {
		if(target == 0) {
			res.add(new ArrayList<Integer>(list));
			return;
		}
		for(Integer num: input.toArray(new Integer[input.size()])) {
			if(num <= target) {
				list.add(num);
				input.remove(num);
				dfsNonReusableNoDup(res, list, input, target - num);
				list.remove(list.size()-1);
				input.add(num);
			}
		}
	}
	
	public static List<List<Integer>> findAllCombinationSumNonReusableWithDup(int[] input, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(input == null || input.length ==0) return res;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(Integer num : input) {
			if(map.containsKey(num)) map.put(num, map.get(num) + 1);
			else map.put(num, 1);
		}
		dfsNonReusableWithDup(res, new ArrayList<Integer>(), map, target);
		return res;
	}
	
	private static void dfsNonReusableWithDup(List<List<Integer>> res, List<Integer> list, HashMap<Integer, Integer> map, int target) {
		if(target == 0) {
			res.add(new ArrayList<Integer>(list));
			return;
		}
		for(Integer num: map.keySet()) {
			if(num <= target && map.get(num) >0) {
				list.add(num);
				map.put(num, map.get(num) -1);
				dfsNonReusableWithDup(res, list, map, target - num);
				list.remove(list.size()-1);
				map.put(num, map.get(num) +1);
			}
		}
	}
}
