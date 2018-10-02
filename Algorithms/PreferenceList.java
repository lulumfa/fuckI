// O(n) space O(n)

// if need break tie, then worst case O(nlgn) when every nodes are in queue

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
    PreferenceList pl = new PreferenceList();
    List<List<Integer>> preferences= new ArrayList<List<Integer>>();
    preferences.add(new ArrayList<Integer>(Arrays.asList(3, 5, 7, 9)));
    preferences.add(new ArrayList<Integer>(Arrays.asList(2, 3, 8)));
    preferences.add(new ArrayList<Integer>(Arrays.asList(5, 8)));
    System.out.println(pl.getPreference(preferences));
    System.out.println(pl.getPreferenceWithBreakTie(preferences, 1));
  }
  
}

class PreferenceList {

  public List<Integer> getPreferenceWithBreakTie(List<List<Integer>> preferences, int breakTieIdx) {
    if (preferences == null || preferences.size() == 0 || breakTieIdx < 0 || breakTieIdx >= preferences.size()) return null;
    Map<Integer, Integer> inDegree = new HashMap<>();
    Map<Integer, Set<Integer>> nodes = new HashMap<>();
    for (List<Integer> l : preferences) {
      for (int i = 0; i < l.size() - 1; i++) {
      int from = l.get(i);
      int to = l.get(i + 1);
      if (!nodes.containsKey(from)) {
      inDegree.put(from, 0);
      nodes.put(from, new HashSet<>());
      }
      if (!nodes.containsKey(to)) {
      inDegree.put(to, 0);
      nodes.put(to, new HashSet<>());
      }
      if (!nodes.get(from).contains(to)) {
      Set<Integer> s = nodes.get(from);
      s.add(to);
      nodes.put(from, s);
      }
      inDegree.put(to, inDegree.getOrDefault(to, 0) + 1);
      }
    }
    Set<Integer> breakTieRule = new HashSet<Integer>(preferences.get(breakTieIdx));
    Queue<Integer> q = new PriorityQueue<Integer>(new Comparator<Integer>() {
      @Override
      public int compare(Integer a, Integer b) {
        if (breakTieRule.contains(a)) return -1;
        if (breakTieRule.contains(b)) return -1;
        return Integer.compare(a, b);
      }
    });
    for (int k : inDegree.keySet()) {
    if (inDegree.get(k) == 0) {
    q.offer(k);
    }
    }
    List<Integer> res = new ArrayList<>();
    while (!q.isEmpty()) {
    int id = q.poll();
    res.add(id);
    Set<Integer> neighbors = nodes.get(id);
    for (int next : neighbors) {
    int degree = inDegree.get(next) - 1;
    inDegree.put(next, degree);
    if (degree == 0) q.offer(next);
    }
    }
    return res;
    }
  
  public List<Integer> getPreference(List<List<Integer>> preferences) {
    Map<Integer, Integer> inDegree = new HashMap<>();
    Map<Integer, Set<Integer>> nodes = new HashMap<>();
    for (List<Integer> l : preferences) {
    for (int i = 0; i < l.size() - 1; i++) {
    int from = l.get(i);
    int to = l.get(i + 1);
    if (!nodes.containsKey(from)) {
    inDegree.put(from, 0);
    nodes.put(from, new HashSet<>());
    }
    if (!nodes.containsKey(to)) {
    inDegree.put(to, 0);
    nodes.put(to, new HashSet<>());
    }
    if (!nodes.get(from).contains(to)) {
    Set<Integer> s = nodes.get(from);
    s.add(to);
    nodes.put(from, s);
    }
    inDegree.put(to, inDegree.getOrDefault(to, 0) + 1);
    }
    }
    Queue<Integer> q = new LinkedList<>();
    for (int k : inDegree.keySet()) {
    if (inDegree.get(k) == 0) {
    q.offer(k);
    }
    }
    List<Integer> res = new ArrayList<>();
    while (!q.isEmpty()) {
    int id = q.poll();
    res.add(id);
    Set<Integer> neighbors = nodes.get(id);
    for (int next : neighbors) {
    int degree = inDegree.get(next) - 1;
    inDegree.put(next, degree);
    if (degree == 0) q.offer(next);
    }
    }
    return res;
    }
}


