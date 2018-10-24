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

// dp, runtime O(k * |flights|) = O(k * n^2), space (n) or kn if not single row
// https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-787-cheapest-flights-within-k-stops/

package airbnb;

import java.util.*;

public class FLightsKStepsDP {

    public static void main(String[] args) {
//        int[][] flights = new int[][] {
//                {0, 1, 100},
//                {0, 2, 500},
//                {1, 2, 100},
//                {0, 3, 700},
//                {2, 3, 50},
//                {1, 3, 200}
//        };

        int[][] flights = new int[][] {
                {0, 2, 100},
                {2, 1, 100},
                {1, 3, 50},
                {0, 3, 1000}
        };

        FLightsKStepsDP flightsKStepsDP = new FLightsKStepsDP();

        System.out.println(flightsKStepsDP.findKStepsMinPriceDP1D(flights, 0, 3, 2));
        System.out.println(flightsKStepsDP.findKStepsMinPriceDP1DWithPath(flights, 0, 3, 2));
        System.out.println(flightsKStepsDP.findKStepsMinPriceDP1D(flights, 0, 3, 1));
        System.out.println(flightsKStepsDP.findKStepsMinPriceDP1DWithPath(flights, 0, 3, 1));
        System.out.println(flightsKStepsDP.findKStepsMinPriceDP1D(flights, 0, 3, 0));
        System.out.println(flightsKStepsDP.findKStepsMinPriceDP1DWithPath(flights, 0, 3, 0));
//        System.out.println(flightsKStepsDP.findCheapestPricePath(3, flights, 0, 2, 1));
    }

    public int findKStepsMinPrice(int[][] flights, int src, int dst, int k) {
        if (flights == null || flights.length == 0) return -1;

        int n = 0;
        for (int[] flight : flights) n = Math.max(n, Math.max(flight[0], flight[1]));
        n++;
        int[][] dp = new int[n][k+2];
        int inf = 1 << 30;
        for (int[]  record : dp) Arrays.fill(record, inf);

        dp[src][0] = 0;

        for (int i = 1; i <= k + 1; i++) {
            dp[src][i] = 0;
            for (int[] flight : flights) {
                dp[flight[1]][i] = Math.min(dp[flight[1]][i], flight[2] + dp[flight[0]][i-1]);
            }
        }
        return dp[dst][k+1] >= inf ? -1 : dp[dst][k+1];
    }

    public int findKStepsMinPriceDP1D(int[][] flights, int src, int dst, int k) {
        if (flights == null || flights.length == 0) return -1;

        int n = 0;
        for (int[] flight : flights) n = Math.max(n, Math.max(flight[0], flight[1]));
        n++;
        int[] dp = new int[n];
        int inf = 1 << 30;
        Arrays.fill(dp, inf);
        dp[src] = 0;

        for (int i = 1; i <= k + 1; i++) {
            int[] next = dp.clone();
            next[src] = 0;
            for (int[] flight : flights) {
                next[flight[1]] = Math.min(next[flight[1]], flight[2] + dp[flight[0]]);
            }
            dp = next;
        }
        return dp[dst] >= inf ? -1 : dp[dst];
    }

    public List<Integer> findKStepsMinPriceDP1DWithPath(int[][] flights, int src, int dst, int k) {
        if (flights == null || flights.length == 0) return null;

        int n = 0;
        for (int[] flight : flights) n = Math.max(n, Math.max(flight[0], flight[1]));
        n++;
        int[] dp = new int[n];
        int inf = 1 << 30;
        Arrays.fill(dp, inf);
        dp[src] = 0;
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int i = 1; i <= k + 1; i++) {
//            int[] next = dp.clone();
            int[] next = new int[n];
            Arrays.fill(next, inf);
            next[src] = 0;
            for (int[] flight : flights) {
                if (next[flight[1]] > flight[2] + dp[flight[0]]) {
                    next[flight[1]] = flight[2] + dp[flight[0]];
                    parent[flight[1]] = flight[0];
                }
            }
            dp = next;
        }

        if ( dp[dst] >= inf) return null;

        List<Integer> res = new ArrayList<>();

        int id = dst;
        while (id != src) {
            res.add(id);
            id = parent[id];
        }
        res.add(src);

        Collections.reverse(res);

        return res;
    }
}

// if using string as city names, which making the dp a bit hard
package airbnb;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightsKStops {

    public static void main(String[] args) {
        List<String> flights = Arrays.asList("A->B,100", "B->C,100", "A->C,500", "B->D,10", "D->C,10");
        FlightsKStops flightsKStops = new FlightsKStops();

        System.out.println(flightsKStops.minCostWithAtMostKStopsLinearFLights(flights, "A", "C", -5));
    }

    // dp[i][j] = min(dp[i-1], dist(a to j) + dp[i-1][a]
    public int minCostWithAtMostKStopsLinearFLights(List<String> flights, String source, String target, int k) {
        if (flights == null || flights.size() == 0 || source == null || target == null || k < 0) return -1;

        Line[] lines = new Line[flights.size()];
        Map<String, Integer> dp = new HashMap<>();
        int idx = 0;
        int INF = 1 << 30;

        for (String flight : flights) {
            if (flight == null) continue;
            String[] segments = flight.split(",");
            if (segments.length != 2) continue;
            String[] cities = segments[0].split("->");
            if (cities.length != 2) continue;
            String from = cities[0], to = cities[1];
            int price = Integer.valueOf(segments[1]);

            if (!dp.containsKey(from)) dp.put(from, INF);
            if (!dp.containsKey(to)) dp.put(to, INF);

            lines[idx++] = new Line(from, to, price);
        }

        dp.put(source, 0);

        for (int i = 0; i <= k; i++) {
            Map<String, Integer> next = (Map<String, Integer>) ((HashMap<String, Integer>) dp).clone();
            next.put(source, 0);
            for (Line line : lines) {
                next.put(line.to, Math.min(next.get(line.to), dp.get(line.from) + line.price));
            }
            dp = next;
        }
        return dp.get(target) >= INF ? -1 : dp.get(target);
    }
}

class Line {
    String from;
    String to;
    int price;

    public Line(String from, String to, int price) {
        this.from = from;
        this.to = to;
        this.price = price;
    }
}
