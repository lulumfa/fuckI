// O(V) if no cycle
// when it comes with cycle or cycles, and when cycles connected with each other, there is no easy way to find who to visit first
// and we would have to revisit visited vertices to remove from the res if found them as downsteam later.
// and in this case, the runtime can be very large, worst case going through cycles from lower to upper always, so, O(V2)

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

public class Solution {
  
    public static void main(String[] args) {
//    int[][] edgess = new int[][] {
//      {0, 1}, 
//      {1, 2},
//      {2, 3},
//      {3, 4}
//    };
//    
//    int n = 5;
//    
//    MinimumVerticesToTraverseGraph m = new MinimumVerticesToTraverseGraph();
//  
//    System.out.println(m.getMinVertices(edgess, n));
//    
//        int[][] edges = {{0, 0}, {1, 2}, {2, 0}, {2, 3}, {3, 1}};
//    System.out.println(m.getMinVertices(edges, 4));
//    
//        edges = new int[][]{{0, 1}, {1, 0}, {2, 1}, {2, 3}, {3, 2}};
//    System.out.println(m.getMinVertices(edges, 4));
    
    int[][] edgess = new int[][] {
      {0, 1}, 
      {1, 2},
      {2, 3},
      {3, 4}
    };
    
    int n = 5;
    
    MinimumVerticesToTraverseGraph m = new MinimumVerticesToTraverseGraph();
  
    System.out.println(m.getMinVerticesWithRevisiting(edgess, n));
    
    // upper cycle and lower cycle, with self cycle
        int[][] edges = {{0, 0}, {1, 2}, {2, 0}, {2, 3}, {3, 1}};
    System.out.println(m.getMinVerticesWithRevisiting(edges, 4));
    
    // upper cycle and lower cycle
        edges = new int[][]{{0, 1}, {1, 0}, {2, 1}, {2, 3}, {3, 2}};
    System.out.println(m.getMinVerticesWithRevisiting(edges, 4));
    
    // single source with cycle at the end
        edges = new int[][]{{0, 1}, {1, 0}, {2, 1}, {3, 1}, {3, 2}};
    System.out.println(m.getMinVerticesWithRevisiting(edges, 4));
  }
}

class MinimumVerticesToTraverseGraph {

  
  
  /*
   * 1. Need to remove visited node (not for cycle start point), because when there is no inbound = 0, 
   * there is no guarantee which cycle/point will be executed first, if the downstream cycle got executed first,
   * we need to remove it when running the upperstream cycle
   * 2. Need to keep the start to avoid deleting the cycle start, or nothing will be recorded for cycles
   * 3. Need to differentiate between current path and global visited nodes, because when visiting the upper cycle late, 
   * then we need to guarantee to re-run the downstream cycle entirely to remove everything from res
   */
  public Set<Integer> getMinVerticesWithRevisiting(int[][] edges, int size) {
    Set<Integer> res = new HashSet<Integer>();
    if (edges == null || edges.length == 0) return res;
    Map<Integer, Set<Integer>> adjacencyLists = new HashMap<Integer, Set<Integer>>();
    for (int i = 0; i < size; i++) adjacencyLists.put(i, new HashSet<Integer>());
    for (int[] edge : edges) {
      if (edge != null && edge.length == 2) {
        adjacencyLists.get(edge[0]).add(edge[1]);
      }
    }
    
    Set<Integer> visited = new HashSet<Integer>();
    for (int i = 0; i < size; i++) {
      if (!visited.contains(i)) {
        res.add(i);
        visited.add(i);
        dfsWithRevisiting(res, adjacencyLists, visited, i, i, new HashSet<>());
      }
    }
    
    return res;
  }
  
  
  private void dfsWithRevisiting(Set<Integer> res, Map<Integer, Set<Integer>> adjacencyLists, Set<Integer> visited, int i, int start,
      Set<Integer> visiting) {
    visiting.add(i);
    visited.add(i);

    for (Integer next : adjacencyLists.get(i)) {
      if (res.contains(next) && start != next) {
        res.remove(next);
      }
      if (!visiting.contains(next)) {
        dfsWithRevisiting(res, adjacencyLists, visited, next, start, visiting);
      }

    }
  }

  /*
   * 1. Need to remove visited node (not for cycle start point), because when there is no inbound = 0, 
   * there is no guarantee which cycle/point will be executed first, if the downstream cycle got executed first,
   * we need to remove it when running the upperstream cycle
   * 2. Need to keep the start to avoid deleting the cycle start, or nothing will be recorded for cycles
   * 3. Need to differentiate between current path and global visited nodes, because when visiting the upper cycle late, 
   * then we need to guarantee to re-run the downstream cycle entirely to remove everything from res
   */
  public Set<Integer> getMinVertices(int[][] edges, int n) {
    Set<Integer> res = new HashSet<Integer>();
    if (edges == null || edges.length == 0) return res;
    
    Map<Integer, DirectedGraphNode> map = new HashMap<Integer, DirectedGraphNode>();
    Set<Integer> unvisited = new HashSet<Integer>();
    for (int i = 0; i < n; i++) unvisited.add(i);
    
    // build the graph vertices, do not to use edges to build vertices as there might be isolated points that wont be surfaced with edges
    for (int i = 0; i < n; i++) {
      map.put(i, new DirectedGraphNode(i));
    }
    // build the graph edges
    for (int[] edge : edges) {
      if (edge != null && edge.length == 2) {
        int start = edge[0], end = edge[1];
        
        map.get(start).adjacencyList.add(map.get(end));
        map.get(end).inbound++;
      }
    }
    
    for (DirectedGraphNode node : map.values()) {
      if (node.inbound == 0) {
        res.add(node.value);
        unvisited.remove(node.value);
        dfsDegreeWay(node, unvisited, res, node.value);
      }
    }
    
    while (!unvisited.isEmpty()) {
      Iterator<Integer> iter = unvisited.iterator();
      int value = iter.next();
      res.add(value);
      unvisited.remove(value);
      dfsDegreeWay(map.get(value), unvisited, res, value);
    }
    
    
    return res;
  }
  
  private void dfsDegreeWay(DirectedGraphNode node, Set<Integer> unvisited, Set<Integer> res, int start) {
    for (DirectedGraphNode next : node.adjacencyList) {
      if (unvisited.contains(next.value)) {
        unvisited.remove(next.value);
        dfsDegreeWay(next, unvisited, res, start);
      } else if (res.contains(next.value) && next.value != start) {
        res.remove(next.value);
      }
    }
  }
  
  class DirectedGraphNode {
    int value;
    int inbound;
    List<DirectedGraphNode> adjacencyList;
    
    public DirectedGraphNode(int value) {
      this.value = value;
      adjacencyList = new ArrayList<DirectedGraphNode>();
    }
  }
}
