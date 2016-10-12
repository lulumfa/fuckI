// O(n), space O(n)

package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class SnapCycle {
	List<SnapCycle> recipents;
	State state;
	
	public SnapCycle() {
		this.state = State.UNVISITED;
		this.recipents = new ArrayList<SnapCycle>();
	}
	
	boolean hasCycle() {		
		return dfs(this);
	}
	
	private boolean dfs(SnapCycle node) {
		node.state = State.VISITING;
		for(SnapCycle recipent: node.recipents) {
			if(recipent.state == State.VISITING) return true;
			else if(recipent.state == State.UNVISITED) {
				if(dfs(recipent)) return true;
			}
		}
		node.state = State.VISITED;
		return false;
	}
	
	public static void main(String[] args) {
		SnapCycle node1 = new SnapCycle();
		SnapCycle node2 = new SnapCycle();
		SnapCycle node3 = new SnapCycle();
		SnapCycle node4 = new SnapCycle();
		SnapCycle node5 = new SnapCycle();
		SnapCycle node6 = new SnapCycle();
		SnapCycle node7 = new SnapCycle();
		SnapCycle node8 = new SnapCycle();
		
		node1.recipents.add(node2);
		node2.recipents.add(node3);
		node3.recipents.add(node4);
		node3.recipents.add(node7);
		node7.recipents.add(node8);
		node8.recipents.add(node6);
		node3.recipents.add(node6);

		System.out.println(node1.hasCycle());
	}
}

enum State {
	UNVISITED,
	VISITING,
	VISITED;
}
