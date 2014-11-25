/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node==null) return node;
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        UndirectedGraphNode head = new UndirectedGraphNode(node.label);
        map.put(node,head);
        Stack<UndirectedGraphNode> stack = new Stack<UndirectedGraphNode>();
        stack.push(node);
        while(!stack.isEmpty()) {
            UndirectedGraphNode temp = stack.pop();
            List<UndirectedGraphNode> neighbors = temp.neighbors;
            for(UndirectedGraphNode neighbor: neighbors) {
                if(map.get(neighbor)==null) {
                    UndirectedGraphNode copy = new UndirectedGraphNode(neighbor.label);
                    map.put(neighbor, copy);
                    map.get(temp).neighbors.add(map.get(neighbor));
                    stack.push(neighbor);
                } else {
                    map.get(temp).neighbors.add(map.get(neighbor));
                }
            }
        }
        return head;
    }
}
