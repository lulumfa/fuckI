//http://www.cnblogs.com/grandyang/p/8460541.html
//https://zxi.mytechroad.com/blog/simulation/leetcode-755-pour-water/

//Time complexity: O(V*n)
//Space complexity: O(1) if no need to print, because we need to separate drops matric,so extra space O(n)
// no unnecessary move

import java.io.*;
import java.util.*;
import org.junit.*;
/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

public class Solution {
  
  
  public static void main(String[] args) {
    int[] heights = new int[] {5,4,2,1,2,3,2,1,0,1,2,4};
    PourWater pw = new PourWater();
    pw.pourWater(heights, 0, 5);
    pw.pourWater(heights, 8, 5);
  }
}

class PourWater {
  
  public void pourWater(int[] heights, int V, int K) {
        if (heights == null || K < 0 || K >= heights.length) return;
        int[] drops = new int[heights.length];
        // printDrops(heights, drops);
        for (int i = 0; i < V; i++) {
            oneDrop(heights, drops, K);
            // printDrops(heights, drops);
        }
        printDrops(heights, drops);
    }

  private void oneDrop(int[] heights, int[] drops, int K) {
        int lowIdx = K;
        for (int i = K; i > 0; i--) {
            if ((heights[i -1] + drops[i-1]) > (heights[i] + drops[i])) break;
            if ((heights[i -1] + drops[i-1]) < (heights[i] + drops[i])) {
                lowIdx = i - 1;
            }
        }
        if (lowIdx < K) {
          drops[lowIdx]++;
            return;
        }
        
        for (int i = K; i < heights.length -1; i ++) {
            if ((heights[i + 1] + drops[i+1]) > (heights[i] + drops[i])) break;
            if ((heights[i + 1] + drops[i+1])  < (heights[i] + drops[i])) {
                lowIdx = i + 1;
            }
        }
      drops[lowIdx]++;
    }
  
    private void printDrops(int[] heights, int[] drops) {
    int n = heights.length;
    int maxHeight = 0;
    for (int i = 0; i < n; i++) {
      maxHeight = Math.max(maxHeight, heights[i] + drops[i]);
    }
    
    for (int i = maxHeight; i >=-1; i--) {
      for (int j = 0; j < n; j++) {
        if (i == -1) System.out.print(j%10);
        else if (i > heights[j] + drops[j]) System.out.print(" ");
        else if (i > heights[j]) System.out.print("w");
        else System.out.print("+");
      }
      System.out.println();
    }
  }
}

// move all the way to the left or right even equals, not likely


