
// O(s.len * p.len), same as space

import java.io.*;
import java.util.*;
import org.junit.*;
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

// from the normal regex matching question, need to add + logic
        // dp[i+1][j+1] corresponds to s[i] and p[j]
        // if p[j] != "*"  dp[i][j] = true && (s[i] == p[j] || p[j] == '.')
        // if p[j] == "*" 
        //      if p[j-1] != "." : dp[i][j+1] && s[i] == s[i-1] && p[j-1] == s[i-1] || dp[i+1][j-1] || dp[i+1][j]
        //      if p[j-1] == '.' : dp[i+1][j-1] || dp[i+1][j], dp[i+2][j+1],....
        
        // "" -> "" true
        // ""-> "*" or "a" -> "*a" should be false
        // abc -> abc true
        // abcd -> abc false
        // xx -> x* true
        // xxsdsdsd -> .* true
        // xxy -> a*x*y true

public class Solution {
  
  public static void main(String[] args) {
    System.out.println(Solution.regMatch("saaaa", "s+a*")); // true
    System.out.println(Solution.regMatch("saaaa", "s+b*")); // false
    System.out.println(Solution.regMatch("saaaab", "s+a*")); // false
    System.out.println(Solution.regMatch("saaaab", "s+b*")); // false
    System.out.println(Solution.regMatch("a", ".*")); // true
    System.out.println(Solution.regMatch("afdfsdfd dvdv", ".*")); // true
  }
  
  public static boolean regMatch(String s, String p) {
      if (s == null || p == null) return false;
      boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
      dp[0][0] = true;
      for (int i = 1; i <= p.length(); i++) {
          if (p.charAt(i - 1) == '*' && dp[i - 2][0]) dp[i][0] = true;
      }

      for (int i = 1; i <= p.length(); i++) {
          for (int j = 1; j <= s.length(); j++) {
              if (p.charAt(i - 1) == '.' || p.charAt(i - 1) == s.charAt(j - 1)) {
                  dp[i][j] = dp[i - 1][j - 1];
              } else if (p.charAt(i - 1) == '*' || p.charAt(i - 1) == '+') {
                  if (p.charAt(i - 2) == '.' || p.charAt(i - 2) == s.charAt(j - 1)) {
                      if (p.charAt(i - 1) == '*') {
                          dp[i][j] = dp[i - 2][j] || dp[i - 2][j - 1] || dp[i][j - 1];
                      } else {
                          dp[i][j] = dp[i - 2][j - 1] || dp[i][j - 1];
                      }
                  } else {
                      dp[i][j] = p.charAt(i - 1) == '*' && dp[i - 2][j];
                  }
              }
          }
      }
      return dp[p.length()][s.length()];
  }
}





