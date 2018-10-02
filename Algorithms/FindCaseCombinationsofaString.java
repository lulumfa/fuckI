// O(2^n * n) *n because of stringbuilder

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
    System.out.println(String.valueOf(Solution.findCasePermutations("ab1c")));
    System.out.println(String.valueOf(Solution.findCasePermutations(null)));
    System.out.println(String.valueOf(Solution.findCasePermutations("")));
    System.out.println(String.valueOf(Solution.findCasePermutations("ABC")));
    System.out.println(String.valueOf(Solution.findCasePermutations("AAA")));
    System.out.println(String.valueOf(Solution.findCasePermutations("123")));
    System.out.println(String.valueOf(Solution.findCasePermutations("!!!")));
  }
    
  public static List<String> findCasePermutations(String s) {
    List<String> res = new ArrayList<String>();
    if (s == null) return res;
    
    int bits = 0;
    int n = s.length();
    for (int i = 0; i < n; i++) {
      if (Character.isLetter(s.charAt(i))) {
        bits++;
      }
    }
    
    for (int i = 0; i < (1 << bits); i++) {
      int cBit = 0; // start from the leftmost for convenience of stringbuilder
      
      StringBuilder sb = new StringBuilder();
      for (int j = 0; j < n; j++) {
        char c = s.charAt(j);
        if (Character.isLetter(c)) {
          if ((i >> cBit++ & 1) == 1) {
            sb.append(Character.toUpperCase(c));
          } else {
            sb.append(Character.toLowerCase(c));
          }
        } else {
          sb.append(c);  
        }
      }
      res.add(sb.toString());
    }
    return res;
  }
}


// iterative way
class Solution {
    public List<String> letterCasePermutation(String S) {
        List<StringBuilder> ans = new ArrayList();
        ans.add(new StringBuilder());

        for (char c: S.toCharArray()) {
            int n = ans.size();
            if (Character.isLetter(c)) {
                for (int i = 0; i < n; ++i) {
                    ans.add(new StringBuilder(ans.get(i)));
                    ans.get(i).append(Character.toLowerCase(c));
                    ans.get(n+i).append(Character.toUpperCase(c));
                }
            } else {
                for (int i = 0; i < n; ++i)
                    ans.get(i).append(c);
            }
        }

        List<String> finalans = new ArrayList();
        for (StringBuilder sb: ans)
            finalans.add(sb.toString());
        return finalans;
    }
}
