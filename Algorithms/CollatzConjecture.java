// http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=273149

// big O would be hard  to estimate, as it sounds like random, but after playing around the methods,
// 10,000 -> 262 steps, 100,000 -> 351 steps, 1,000,000 stackoverfloow

// be careful of the memo part, needs to update the memo on every intermediate res and take use of them

import java.io.*;
import java.util.*;
import org.junit.*;
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  
  public static void main(String[] args) {
    System.out.println(findLongestSteps(7));
    System.out.println(findLongestSteps(1));
    System.out.println(findLongestSteps(0));
    System.out.println(findLongestSteps(-2));
    System.out.println(findLongestSteps(100000));
  }
  
  public static int findLongestSteps(int n) {
    if (n < 1) return -1;
    int max = 0;
    Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
    for (int i = 1; i <= n; i++) {
      int steps = recursivelyFindSteps(i, memo);
      memo.put(i, steps); 
      max = Math.max(max, steps);
    }
    
    return max;
  }
  
  private static int recursivelyFindSteps(int n, Map<Integer, Integer> memo) {
    if (n == 1) return 1;
    if (memo.containsKey(n)) return memo.get(n);
    int next = n % 2 == 1 ? 3 * n + 1 : n /2;
    if (memo.containsKey(next)) return 1 + memo.get(next);
    int rec = recursivelyFindSteps(next, memo);
    memo.put(next, rec);
    return 1 + rec;
  }
}

