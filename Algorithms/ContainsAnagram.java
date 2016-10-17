// 2n = O(n) , space (k) k = length of target

package Leetcode;

import java.util.HashMap;

public class Anagram {
	public static void main(String[] args) {
		System.out.println(hasAnagram("abcddesss", "decbd"));
	}
	
	public static String hasAnagram(String s, String target) {
		String anagram = null;
		if(s == null || target == null) return anagram;
		
		HashMap<Character, Integer> countMap = new HashMap<Character, Integer>();
		for(int i = 0; i< target.length(); i++) {
			if(countMap.containsKey(target.charAt(i))) countMap.put(target.charAt(i), countMap.get(target.charAt(i)) + 1);
			else {
				countMap.put(target.charAt(i), 1);
			}
		}
		int total = target.length();
		int left = 0; 
		int right = 0;
		
		for(; right < s.length(); right++) {
			char cur = s.charAt(right);
			if(!countMap.containsKey(cur)) {
				while(left != right +1) {
					if(countMap.containsKey(s.charAt(left))) {
						countMap.put(s.charAt(left), countMap.get(s.charAt(left)) + 1);
						total++;
					}
					left++;
				}
			} else if(countMap.get(cur) == 0) {
				while(countMap.get(cur) == 0) {
					countMap.put(s.charAt(left), countMap.get(s.charAt(left)) + 1);
					left++;
					total++;
				}
			} else {
				countMap.put(cur, countMap.get(cur) -1);
				total--;
				if(total == 0) return s.substring(left, right+1);
			}
		}
		return anagram;
	}
}
