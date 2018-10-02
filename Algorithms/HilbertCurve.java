// O(iter)
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
    System.out.println(HilbertCurve.findPositionOnHilbertCurve(1, 1, 2));
    System.out.println(HilbertCurve.findPositionOnHilbertCurve(0, 1, 1));
    System.out.println(HilbertCurve.findPositionOnHilbertCurve(2, 2, 2));
  }
}

class HilbertCurve {
  
  public static int findPositionOnHilbertCurve(int x, int y, int iter) {
    if (iter <= 0) return 1;
    int side_len = 1 << (iter -1);
    int unit_size = 1 << (2 * (iter - 1));
    
    if (x >= side_len && y >= side_len) {
      // 3rd quadrant, same direction
      return 2 * unit_size + findPositionOnHilbertCurve(x - side_len, y - side_len, iter -1);
    } else if (x >= side_len && y < side_len) {
      // 4th quadrant, rotation required
      return 3 * unit_size + findPositionOnHilbertCurve(2 * side_len - y, 2 * side_len - x, iter -1);
    } else if (x < side_len && y >= side_len) {
      // 2rd quadrant, same direction
      return unit_size + findPositionOnHilbertCurve(x, y - side_len, iter -1);
    } else {
      // 1st quadrant, rotation required
      return findPositionOnHilbertCurve(x, y, iter -1);
    }
  }
}



