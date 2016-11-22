package Snapchat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Bipartite {
	public static void main(String[] args) {
		GraphNode node1 = new GraphNode();
		GraphNode node2 = new GraphNode();
		GraphNode node3 = new GraphNode();
		GraphNode node4 = new GraphNode();
		GraphNode node5 = new GraphNode();
		GraphNode node6 = new GraphNode();
		
		node1.neighbors.add(node2);
		node2.neighbors.add(node1);
		node2.neighbors.add(node3);
		node3.neighbors.add(node2);
		node3.neighbors.add(node4);
		node4.neighbors.add(node3);
		node4.neighbors.add(node5);
		node5.neighbors.add(node4);
		node5.neighbors.add(node1);
//		node6.neighbors.add(node5);
//		node6.neighbors.add(node1);
		node1.neighbors.add(node5);
		
		Bipartite bp = new Bipartite();
		System.out.println(bp.isBipartite(node1));
	}
	
	public boolean isBipartite(GraphNode node) {
		if(node == null) return false;
		
		Queue<GraphNode> queue = new LinkedList<GraphNode>();
		node.setColor(0);
		queue.offer(node);
		while(!queue.isEmpty()) {
			GraphNode cur = queue.poll();
			for(GraphNode neighbor : cur.neighbors) {
				if(neighbor.getColor() == cur.getColor()) return false;
				else if(neighbor.getColor() == null) {
					neighbor.setColor(1- cur.getColor());
					queue.offer(neighbor);
				}
			}
		}
		return true;
	}
	
}

class GraphNode{
	
	private Integer color;
	List<GraphNode> neighbors;
	
	public GraphNode() {
		this.neighbors = new ArrayList<GraphNode>();
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public Integer getColor() {
		return this.color;
	}
	
	@Override
	public String toString() {
		return String.valueOf(color);
	}
}
