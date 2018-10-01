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

// using the heap to optimize the worst case O(n^2) to nlogn

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
    // List<String> items = new ArrayList<String>(Arrays.asList(
    //             "1,28,310.6,SF",
    //             "4,5,204.1,SF",
    //             "20,7,203.2,Oakland",
    //             "6,8,202.2,SF",
    //             "6,10,199.1,SF",
    //             "1,16,190.4,SF",
    //             "6,29,185.2,SF",
    //             "7,20,180.1,SF",
    //             "6,21,162.1,SF",
    //             "2,18,161.2,SF",
    //             "2,30,149.1,SF",
    //             "3,76,146.2,SF",
    //             "2,14,141.1,San Jose"
    // ));
    List<String> items = new ArrayList<String>(Arrays.asList(
                "1,28,310.6,SF",
                "2,5,204.1,SF",
                "1,7,203.2,Oakland",
                "2,8,202.2,SF",
                "1,10,199.1,SF",
                "2,16,190.4,SF",
                "1,29,185.2,SF",
                "2,20,180.1,SF",
                "1,21,162.1,SF",
                "2,18,161.2,SF",
                "1,30,149.1,SF",
                "2,76,146.2,SF",
                "1,14,141.1,San Jose"
    ));
    
    List<String> res = Solution.pagination(items, 4);
    for (String s : res) {
      System.out.println(s);
    }
  }

  
  public static List<String> pagination(List<String> items, int pageSize) {
    List<String> res = new ArrayList<String>();
    if (items == null || items.size() == 0 || pageSize < 1) return res;
    
    Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
    int i = 0;
    for (String item : items) {
      if (item == null) continue;
      String[] segments = item.split(",");
      if (segments.length < 1) continue;
      int id = Integer.valueOf(segments[0]);
      if (!map.containsKey(id)) {
        map.put(id, new LinkedList<Integer>());
      }
      map.get(id).add(i++);
    }
              
    PriorityQueue<Tuple> queue = new PriorityQueue<Tuple>(new Comparator<Tuple>(){
      @Override
      public int compare(Tuple a, Tuple b) {
        return Integer.compare(a.order, b.order);
      } 
    });
              
    for (Integer id : map.keySet()) {
      queue.offer(new Tuple(id, map.get(id).get(0), 0));
    }
    
    int count = 0;
    int dup = 0;
    Set<Integer> visited = new HashSet<Integer>();
    Set<Tuple> stashed = new HashSet<Tuple>();
    while(!queue.isEmpty()) {
      Tuple cur = queue.poll();
      if (!visited.contains(cur.id) || dup > 0) {
        visited.add(cur.id);
        res.add(items.get(map.get(cur.id).get(cur.index)));
        cur.index++;
        count++;
        if (cur.index < map.get(cur.id).size()) {
          if (dup > 0) {
            dup--;
            queue.offer(cur);
          }
          if (dup <= 0) stashed.add(cur);
        }
      }
      if (count == pageSize) {
        visited.clear();
        res.add("********");
        count = 0;
        dup = 0;
        Iterator<Tuple> iter = stashed.iterator();
        while(iter.hasNext()) {
          Tuple t = iter.next();
          iter.remove();
          queue.offer(t);
        }
      } 
      if (queue.isEmpty() && !stashed.isEmpty()) {
        Iterator<Tuple> iter = stashed.iterator();
        while(iter.hasNext()) {
          Tuple t = iter.next();
          iter.remove();
          queue.offer(t);
        }
        dup = pageSize - count;
      }
    }
    
    return res;
  }
}
              
class Tuple {
  int id;
  int order;
  int index;
  
  public Tuple(int id, int order, int index) {
    this.id = id;
    this.order = order;
    this.index = index;
  }
}



