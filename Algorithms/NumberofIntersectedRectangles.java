// O(n*n *lgn) ~O(n2) if consider the path compressed enough n = # of rec

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
    int[][][] rectangles = new int[][][] {
      {{1, 0}, {10, 10}},
      {{-1, -1}, {5, 5}},
      {{1, -10}, {10, 0}}
    };
    
    int[][][] rec2 = new int[][][] {
                    {{-3, -2}, {2, 1}},
                    {{10, 8}, {15, 10}},
                    {{1, 0}, {7, 4}},
                    {{12, 9}, {16, 12}},
                    {{-2, -1}, {5, 3}}
    };
    
    Solution s = new Solution();
    System.out.println(s.findNumberOfIntersectedRectangles(rectangles));
    System.out.println(s.findNumberOfIntersectedRectangles(rec2));
  }
  
  public int findNumberOfIntersectedRectangles(int[][][] rectangles) {
    //union find, weighted quick-find with path compression
    if (rectangles == null) return 0;
    int n = rectangles.length;
    UnionFind uf = new UnionFind(n);
    
    for (int i = 0; i < n; i++) {
      for (int j = i +1; j < n; j++) {
        if (areIntersected(rectangles[i], rectangles[j])) {
          uf.union(i, j);
        }
      }
    }
    return uf.getCount();
  }
  
  private boolean areIntersected(int[][] a, int[][] b) {
    // if one is on the left of the other or one is on the top of the other
    // then they are not intersected. since we will 
    if (a == null || b == null || a.length != 2 || b.length != 2) return false;
    if (a[1][0] <= b[0][0] || b[1][0] <= a[0][0]) return false;
    if (a[0][1] >= b[1][1] || b[0][1] >= a[1][1]) return false;
    return true;
  }
}

class UnionFind {
  
  
  int[] ids;
  int[] weights;
  int count = 0;
  
  public UnionFind(int n) {
    ids = new int[n];
    weights = new int[n];
    
    for (int i = 0; i < n; i++) {
      ids[i] = i;
      weights[i] = 1;
      count++;
    }
  }
  
  public int getCount() {
    return count;
  }
  
  public boolean isConnected(int a, int b) {
    return find(a) == find(b);
  }
  
  public int find(int id) {
    while (ids[id] != id ) {
      ids[id] = ids[ids[id]];
      id = ids[id];
    }
    return id;
  }
  
  public boolean union(int a, int b) {
    int idA = find(a);
    int idB = find(b);
    if (idA == idB) return false;
    
    if (weights[idA] <= weights[idB]) {
      ids[idA] = idB;
      weights[idB] += weights[idA];
    } else {
      ids[idB] = idA;
      weights[idA] += weights[idB];
    }
    count--;
    return true;
  }
}
