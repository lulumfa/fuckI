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
		System.out.println(wg.findMinCost(costList));
	}
	
	public int findMinCost(List<List<Integer>> costList) {
		if (costList == null || costList.size() < 10) return Integer.MAX_VALUE;
		int n = costList.size();
		Wizard[] wizards = new Wizard[n];
		for (int i = 0; i < n; i++) wizards[i] = new Wizard(i, i == 0 ? 0 : Integer.MAX_VALUE);
		
		Queue<Wizard> queue = new LinkedList<Wizard>();
		queue.offer(wizards[0]);
		while(!queue.isEmpty()) {
			Wizard wizard = queue.poll();
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
	
	class Wizard {
		int idx;
		int dist;
		public Wizard(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}
	}
}
