// dijkstra w/ priority queue O(n (logn + E in pq caused by remove)), if the edges are sparse and E is small, then it is close tp nlogn
// if not using the normal array would do O(n * n to find the min from an array) = O(n2)

package Airbnb;

import java.util.*;

public class WizardGraph {

	public static void main(String[] args) {
		List<List<Integer>> costList = new ArrayList<List<Integer>>(10);
		costList.add(Arrays.asList(1));
		costList.add(Arrays.asList(2, 5));
		costList.add(Arrays.asList(9));
		costList.add(Arrays.asList());
		costList.add(Arrays.asList());
		costList.add(Arrays.asList(9));
		costList.add(Arrays.asList());
		costList.add(Arrays.asList());
		costList.add(Arrays.asList());
		costList.add(Arrays.asList());
		
		WizardGraph wg = new WizardGraph();
//		System.out.println(wg.findMinCostPQ(costList, 0, 9));
		System.out.println(wg.findMinCost(costList));
	}
	
	public int findMinCostPQ(List<List<Integer>> costList, int source, int target) {
		if (costList == null) return Integer.MAX_VALUE;
		int n = costList.size();
		Wizard[] wizards = new Wizard[n];
		Set<Wizard> visited = new HashSet<Wizard>();
		for (int i = 0; i < n; i++) {
			wizards[i] = new Wizard(i, i == source ? 0 : Integer.MAX_VALUE);
		}
		PriorityQueue<Wizard> pq = new PriorityQueue<Wizard>();
		pq.offer(wizards[source]);
		while (!pq.isEmpty()) {
			Wizard cur = pq.poll();
			visited.add(cur);
			if (cur.id == target) {
				return (int) cur.dist;
			}
			for (Integer neighbor : costList.get(cur.id)) {
				Wizard next = wizards[neighbor];
				if (!visited.contains(next)) {
					double newDist = cur.dist + Math.pow(next.id - cur.id, 2); 
					if (next.dist > newDist) {
						pq.remove(next);
						next.dist = newDist;
						pq.offer(next);
					}
				}
			}
		}
		return Integer.MAX_VALUE;
	}
	
	class Wizard implements Comparable<Wizard>{
		int id;
		double dist;
		
		public Wizard(int id, double dist) {
			this.id = id;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Wizard that) {
			return Double.compare(this.dist, that.dist);
		}
	}
	public int findMinCost(List<List<Integer>> costList) {
		if (costList == null || costList.size() < 10) return Integer.MAX_VALUE;
		int n = costList.size();
		Wizard1[] wizards = new Wizard1[n];
		for (int i = 0; i < n; i++) wizards[i] = new Wizard1(i, i == 0 ? 0 : Integer.MAX_VALUE);
		
		Queue<Wizard1> queue = new LinkedList<Wizard1>();
		queue.offer(wizards[0]);
		while(!queue.isEmpty()) {
			Wizard1 wizard = queue.poll();
			List<Integer> neighbors = costList.get(wizard.idx);
			for (Integer neighbor : neighbors) {
				int newDist = wizard.dist + (neighbor - wizard.idx) * (neighbor - wizard.idx);
				if (wizards[neighbor].dist > newDist) {
					wizards[neighbor].dist = newDist;
					queue.offer(wizards[neighbor]);
				}
			}
		}
		
		return wizards[n-1].dist;
	}
	
	class Wizard1 {
		int idx;
		int dist;
		public Wizard1(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}
	}
}
