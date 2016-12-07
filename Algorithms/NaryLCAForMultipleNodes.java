// O(nk) k = given nodes length

package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class LCANaryTree {

	public static void main(String[] args) {
		
		
		NaryNode node1 = new NaryNode(1);
		NaryNode node2 = new NaryNode(2);
		NaryNode node3 = new NaryNode(3);
		NaryNode node4 = new NaryNode(4);
		NaryNode node5 = new NaryNode(5);
		NaryNode node6 = new NaryNode(6);
		NaryNode node7 = new NaryNode(7);
		NaryNode node8 = new NaryNode(8);
		NaryNode node9 = new NaryNode(9);
		
		node1.children.add(node2);
		node1.children.add(node3);
		node1.children.add(node4);
		node2.children.add(node5);
		node3.children.add(node6);
		node4.children.add(node7);
		node4.children.add(node8);
		node4.children.add(node9);

		System.out.println(findLCA(node7, node4, node1).val);
		
		NaryNode[] nodes = {node4, node6, node8};
		System.out.println(findLCAForK(nodes, node1).node.val);

	}
	
	public static NaryNode findLCA(NaryNode node1, NaryNode node2, NaryNode root) {
		if(root == null) return null;
		
		if(root == node1 || root == node2) return root;
		int count = 0;
		NaryNode childRoot = null;
		
		for(NaryNode node : root.children) {
			NaryNode temp = findLCA(node1, node2, node);
			if(temp != null) {
				count++;
				childRoot = temp;
			}
		}
		
		if(count == 2) {
			return root;
		}
		
		return childRoot;
	}
	
	
	public static NodeCount findLCAForK(NaryNode[] nodes, NaryNode root) {
		if(root == null) return null;
		
		int count = 0;
		for(NaryNode node : nodes) {
			if(node == root) {
				count++;
			}
		}
		NodeCount res = null;
		NaryNode ans = null;
		if(count > 0) {
			ans = root;
		}
		
		for(NaryNode node : root.children) {
			NodeCount temp = findLCAForK(nodes, node);
			if(temp.count == nodes.length) return temp;
			if(temp != null) {
				count += temp.count;
				if(ans == null) ans = temp.node;
			}
		}
		res = new NodeCount(ans, count);

		if(count == nodes.length) {
			res.node = root;
		}
		
		return res;
	}
}

class NodeCount {
	NaryNode node;
	int count;
	
	public NodeCount(NaryNode node, int count) {
		this.node = node;
		this.count = count;
	}
}

class NaryNode {
	int val;
	List<NaryNode> children;
	
	public NaryNode(int val) {
		this.val = val;
		children = new ArrayList<NaryNode>();
	}
}
