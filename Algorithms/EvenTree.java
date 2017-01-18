// O(|E| + |V|), spaceO(n)

//dfs count and trim, ask if the edge is directed, both ways below

package Facebook;

import java.util.ArrayList;
import java.util.List;

public class EvenTree {
	public static void main(String[] args) {
		int n = 10;
//		int[][] edges = {{2, 1}, {3, 1}, {4, 3}, {5, 2}, {6, 1}, {7, 2}, {8, 6}, {9, 8}, {10, 8}};
		int[][] edges = {{1, 2}, {3, 1}, {4, 3}, {5, 2}, {6, 1}, {7, 2}, {8, 6}, {9, 8}, {10, 8}};

		EvenTree et = new EvenTree();
//		System.out.println(et.trimToEvenTree(edges, n));
		System.out.println(et.trimToEvenTreeUndirectedEdge(edges, n));
	}
	
	// edge no direction, can be reversed
	public int trimToEvenTreeUndirectedEdge(int[][] edges, int n) {
		if(edges == null || edges.length == 0 || edges[0].length == 0 || n <= 0 || n% 2 == 1) return -1;
		
		List<List<Integer>> nodes = new ArrayList<List<Integer>>();
		boolean[] visited = new boolean[n];
		
		for(int i = 0; i < n; i++) {
			nodes.add(new ArrayList<Integer>());
		}
		
		for(int[] edge : edges) {
			nodes.get(edge[1] -1).add(edge[0]);
			nodes.get(edge[0] -1).add(edge[1]);
		}
		
		int[] res = {0};
		
		dfsTrimEdgesUndirectedEdge(nodes, res, 1, visited);
		
		return res[0];
	}
	
	private int dfsTrimEdgesUndirectedEdge(List<List<Integer>> nodes, int[] res, int root, boolean[] visited) {
		int count = 1;
		visited[root -1] = true;
		for(Integer child : nodes.get(root-1)) {
			if(visited[child -1]) continue;
			int childTreeCount = dfsTrimEdgesUndirectedEdge(nodes, res, child, visited);
			if(childTreeCount % 2 == 0) {
				System.out.println(root + "->" + child);
				res[0]++;
			} else {
				count += childTreeCount;
			}
		}
		
		return count;
	}
	
	// edge : A-B
	public int trimToEvenTree(int[][] edges, int n) {
		if(edges == null || edges.length == 0 || edges[0].length == 0 || n <= 0 || n% 2 == 1) return -1;
		
		List<List<Integer>> nodes = new ArrayList<List<Integer>>();
		
		for(int i = 0; i < n; i++) {
			nodes.add(new ArrayList<Integer>());
		}
		
		for(int[] edge : edges) {
			nodes.get(edge[1] -1).add(edge[0]);
		}
		
		int[] res = {0};
		
		dfsTrimEdges(nodes, res, 1);
		
		return res[0];
	}
	
	private int dfsTrimEdges(List<List<Integer>> nodes, int[] res, int root) {
		int count = 1;
		for(Integer child : nodes.get(root-1)) {
			int childTreeCount = dfsTrimEdges(nodes, res, child);
			if(childTreeCount % 2 == 0) {
				System.out.println(root + "->" + child);
				res[0]++;
			} else {
				count += childTreeCount;
			}
		}
		
		return count;
	}
}
