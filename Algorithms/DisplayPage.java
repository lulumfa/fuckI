// O(numOfPage * n) = O(n/size * size) best case, when only 2 or few distinct numbers, 
// then iter will have to go back and force several times and that n/size * size/2 = O(n^2), for that, we can optimize it 
// with split the numbers into sorted id based lists and then maintain a heap to pop value -> O(nlgn)

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
    List<String> items = new ArrayList<String>(Arrays.asList(
                "1,28,310.6,SF",
                "4,5,204.1,SF",
                "20,7,203.2,Oakland",
                "6,8,202.2,SF",
                "6,10,199.1,SF",
                "1,16,190.4,SF",
                "6,29,185.2,SF",
                "7,20,180.1,SF",
                "6,21,162.1,SF",
                "2,18,161.2,SF",
                "2,30,149.1,SF",
                "3,76,146.2,SF",
                "2,14,141.1,San Jose"
    ));
    
    List<String> res = Solution.pagination(items, 4);
    for (String s : res) {
      System.out.println(s);
    }
  }

  
  public static List<String> pagination(List<String> items, int pageSize) {
    List<String> res = new ArrayList<String>();
    if (items == null || items.size() == 0 || pageSize < 1) return res;
    
    Set<String> visited = new HashSet<String>();
    List<String> list = new LinkedList<String>(items);
    Iterator<String> iter = list.iterator();
    boolean hitEnd = false;
    int count = 0;  
    while(iter.hasNext()) {
      String next = iter.next();
      if (next == null) continue;
      String[] segments = next.split(",");
      if (segments.length < 1) continue;
      String id = segments[0];
      if (!visited.contains(id) || hitEnd) {
        res.add(next);
        visited.add(id);
        iter.remove();
        count++;
      }
      
      if (count == pageSize) {
        hitEnd = false;
        visited.clear();
        res.add("*****");
        count = 0;
        iter = list.iterator();
      }
      
      if (!iter.hasNext()) {
        hitEnd = true;
        iter = list.iterator();
      }
    }
    return res;
  }
}

