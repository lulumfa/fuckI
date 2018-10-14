// dijkstra, O(nlog(n + k)), where k is the edges per node, which should be small, but worst case k = n2, then O(2nlogn)
// still better than bfs O(n2)

package Airbnb;

import java.util.*;

class CheapestFlightsWithKStops {
	
	public static void main(String[] args) {
		CheapestFlightsWithKStops cf = new CheapestFlightsWithKStops();
		System.out.println(cf.findCheapestPrice(4, new int[][] {{0, 1, 1}, {0, 2, 5}, {1, 2, 1}, {2, 3, 1}}, 0, 3, 1));
		System.out.println(cf.findCheapestPriceWithOutputSteps(4, new int[][] {{0, 1, 1}, {0, 2, 5}, {1, 2, 1}, {2, 3, 1}}, 0, 3, 1));
	}
	
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
    	 if (flights == null || flights.length == 0) return -1; 
         // building all vertices
         Map<Integer, Map<Integer,Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
         for (int i = 0; i < n; i++) {
             map.put(i, new HashMap<Integer, Integer>());
         }
         
         // building all edges
         for (int[] flight : flights) {
             int from = flight[0];
             int to = flight[1];
             int cost = flight[2];
             map.get(from).put(to, cost);
         }
         
         // dijkstra algo
         PriorityQueue<Stop> queue = new PriorityQueue<Stop>();
         queue.offer(new Stop(src, 0, -1));
         
         while (!queue.isEmpty()) {
             Stop cur = queue.poll();
             if (cur.id == dst) return cur.cost;
             if (cur.steps >= K) {
                 continue;
             }
             for (Integer nei : map.get(cur.id).keySet()) {
                 int newCost = cur.cost + map.get(cur.id).get(nei);
                 queue.offer(new Stop(nei, newCost, cur.steps + 1));
             }
         }
         return -1;
     } 
    
    public List<Integer> findCheapestPriceWithOutputSteps(int n, int[][] flights, int src, int dst, int K) {
   	 if (flights == null || flights.length == 0) return null; 
        // building all vertices
        Map<Integer, Map<Integer,Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
        for (int i = 0; i < n; i++) {
            map.put(i, new HashMap<Integer, Integer>());
        }
        
        // building all edges
        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int cost = flight[2];
            map.get(from).put(to, cost);
        }
        
        // dijkstra algo
        PriorityQueue<Stop1> queue = new PriorityQueue<Stop1>();
        queue.offer(new Stop1(src, 0, -1, new ArrayList() {{add(src);}}));
        
        while (!queue.isEmpty()) {
            Stop1 cur = queue.poll();
            if (cur.id == dst) return cur.traces;
            if (cur.steps >= K) {
                continue;
            }
            for (Integer nei : map.get(cur.id).keySet()) {
                int newCost = cur.cost + map.get(cur.id).get(nei);
                queue.offer(new Stop1(nei, newCost, cur.steps + 1, new ArrayList<Integer>(cur.traces) {{
                	add(nei);
                }}));
            }
        }
        return null;
    }   
	
	
    // dfs with prunning， O(n^(k+1)) , space (k+1)
	class Solution {
    private int min = Integer.MAX_VALUE;
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if (flights == null || flights.length == 0) return -1;
        Map<Integer, List<Tuple>> map = new HashMap<Integer, List<Tuple>>();
        
        for (int[] flight : flights) {
            if (flight == null || flight.length != 3) continue;
            int s = flight[0], dest = flight[1], cost = flight[2];
            List<Tuple> tuple = map.get(s);
            if (tuple == null) {
                tuple = new ArrayList<Tuple>();
            }
            tuple.add(new Tuple(dest, cost));
            map.put(s, tuple);
            if (!map.containsKey(dest)) map.put(dest, new ArrayList<Tuple>());
        }
        
        dfs(map, new boolean[map.size()], src, dst, K + 1, 0);
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    
    private void dfs(Map<Integer, List<Tuple>> map, boolean[] visited, int src, int dst, int steps, int cost) {
        if (src == dst) {
            if (cost < min) {
                min = cost;
            }
            return;   
        }
        if (steps == 0) return;
        visited[src] = true;
        for (Tuple tuple : map.get(src)) {
            if (visited[tuple.dest] || (cost + tuple.price > min)) continue;
            dfs(map, visited, tuple.dest, dst, steps - 1, cost + tuple.price);
        }
        visited[src] = false;
    }
}

class Tuple {
    int dest;
    int price;
    
    public Tuple(int dest, int price) {
        this.dest = dest;
        this.price = price;
    }
}
}

class Stop implements Comparable<Stop> {
    int id;
    int cost;
    int steps;
    
    public Stop(int id, int cost, int step) {
        this.id = id;
        this.cost = cost;
        this.steps = step;
    }
    
    @Override
    public int compareTo(Stop that) {
        return Integer.compare(this.cost, that.cost);
    }
}

class Stop1 implements Comparable<Stop1> {
    int id;
    int cost;
    int steps;
    List<Integer> traces;
    
    public Stop1(int id, int cost, int step, List<Integer> traces) {
        this.id = id;
        this.cost = cost;
        this.steps = step;
       	this.traces = traces;
    }
    
    @Override
    public int compareTo(Stop1 that) {
        return Integer.compare(this.cost, that.cost);
    }
}
