// O(nlg32?) ~ O(n), binary search, at most scan 32 (for odd number, if even then double, but still constant) considering it is integer, 32 bit, Integer.MAX 2^31-1 to Integer.MIN -2^31

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
    Solution m = new Solution();
    System.out.println(m.findMedian(new int[] {8, 7, 3, 2, 1, 4, 5, 6}));
    System.out.println(m.findMedian(new int[] {1, 2, 4, 4, 4, 5, 5, 5, 6}));
  }

  public double findMedian(int[] input) {
    if (input == null) return Integer.MAX_VALUE;
    int len = 0;
    for (Integer num : input) {
      len++;
    }
    return ((len & 1) == 1 ? helper(input, Integer.MIN_VALUE, Integer.MAX_VALUE, len/2 + 1) : (double)(helper(input, Integer.MIN_VALUE, Integer.MAX_VALUE, len/2) + helper(input, Integer.MIN_VALUE, Integer.MAX_VALUE, len/2 + 1)) /2);  
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
    if (count == k) return res;
    else if (count < k) return helper(input, Math.max(res + 1, pivot), high, k);
    else return helper(input, low, res, k);
  }
}



