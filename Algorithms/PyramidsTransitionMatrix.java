// runtime analysis : https://leetcode.com/problems/pyramid-transition-matrix/solution/ 

package Airbnb;

import java.util.*;

public class PyramidTransition {
	
	public static void main(String[] args) {
		String[] lines = {
                "A,A,AC", "A,B,CD", "A,C,D", "A,D,B",
                "B,A,B", "B,B,C", "B,C,A", "B,D,CD",
                "C,A,A", "C,B,C", "C,C,D", "C,D,B",
                "D,A,BC", "D,B,D", "D,C,A", "D,D,C"
        };
		
		PyramidTransition t = new PyramidTransition(lines);
		System.out.println(t.check("ABCD"));
		System.out.println(t.check("AACC"));
		System.out.println(t.check("AAAA"));
		System.out.println(t.check("CCCC"));
		System.out.println(t.check("DDDD"));
		
		System.out.println(t.checkWithTarget("AAAA", 'A'));
		System.out.println(t.checkWithTarget("AAAA", 'B'));
		System.out.println(t.checkWithTarget("AAAA", 'C'));
		
		System.out.println(t.checkWithTarget("CCCC", 'D'));
	}
	
	private static final String SEP = "|";
	
	private Map<String, Character> cacheWithResult; // String -> final character mapping if exists
	private Map<String, Boolean> cache; // String -> final character mapping if exists and qualifies the criteria
	private Map<String, Set<Character>> rules;
	
	public PyramidTransition(String[] input) {
		cache = new HashMap<String, Boolean>();
		cacheWithResult = new HashMap<String, Character>();
		rules = new HashMap<String, Set<Character>>();
		
		for (String rule : input) {
			String[] segments = rule.split(",");
			if (segments.length != 3) continue;
			String key = segments[0] + SEP + segments[1];
			Set<Character> set = new HashSet<Character>();
			for (char c : segments[2].toCharArray()) set.add(c);
			if (!rules.containsKey(key)) rules.put(key, new HashSet<Character>());
			rules.get(key).addAll(set);
		}
	}
	
	public boolean check(String s) {
		cache.clear();
		return search(s, s);
	}
	
	public boolean checkWithTarget(String s, char c) {
		cache.clear();
		return searchWithTarget(s, c);
	}

	private boolean search(String input, String cur) {
		if (cache.containsKey(cur)) return cache.get(cur) ;
		if (cur.length() == 1) {
			cache.put(cur, input.contains(cur));
			return cache.get(cur);
		}
		// pre-processing to save time for last minute failures
		for (int i = 0; i < cur.length() - 1; i++) if (!rules.containsKey(cur.charAt(i) + SEP + cur.charAt(i+1))) return false;
		List<String> nextLines = new ArrayList<String>();
		computeNextLine(cur, nextLines, 0, new StringBuilder());
		for (String line : nextLines) {
			boolean result = search(input, line);
			if (result) {
				cache.put(line, true);
				return true;
			}
		}
		cache.put(cur, false);
		return false;
	}
	
	private boolean searchWithTarget(String cur, Character target) {
		if (cache.containsKey(cur)) return cache.get(cur) ;
		if (cur.length() == 1) {
			cache.put(cur, cur.charAt(0) == target);
			return cache.get(cur);
		}
		// pre-processing to save time for last minute failures
		for (int i = 0; i < cur.length() - 1; i++) if (!rules.containsKey(cur.charAt(i) + SEP + cur.charAt(i+1))) return false;
		List<String> nextLines = new ArrayList<String>();
		computeNextLine(cur, nextLines, 0, new StringBuilder());
		for (String line : nextLines) {
			boolean result = searchWithTarget(line, target);
			if (result) {
				cache.put(line, true);
				return true;
			}
		}
		cache.put(cur, false);
		return false;
	}

	private void computeNextLine(String cur, List<String> res, int start, StringBuilder sb) {
		if (start == cur.length() -1) {
			res.add(sb.toString());
			return;
		}
		String key = cur.charAt(start) + SEP + cur.charAt(start + 1);
		for (Character c : rules.get(key)) {
			sb.append(c);
			computeNextLine(cur, res, start + 1, sb);
			sb.setLength(sb.length() - 1);
		}
	}
}

