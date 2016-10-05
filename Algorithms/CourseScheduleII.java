// Topological sort, O(|E| + |V|), space(O(|E| + |V|)

public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if(numCourses < 1 || prerequisites == null) return new int[0];
        int res = 0;
        int[] resCourses = new int[numCourses];
        Queue<Integer> queue = new LinkedList<Integer>();
        HashMap<Integer, DirectedGraphNode> map = new HashMap<Integer, DirectedGraphNode>();
        
        // construct the graph/node, edges, inbounds
        for(int i = 0; i < numCourses; i++) {
            map.put(i, new DirectedGraphNode(i));
        }
        for(int[] prerequisite: prerequisites) {
            if(prerequisite == null || prerequisite.length <2) return new int[0];
            map.get(prerequisite[0]).inBoundCount++;
            map.get(prerequisite[1]).adjacencyList.add(prerequisite[0]);
        }
        
        // search zero inbound node and push it to the queue for BFS
        for(int i = 0; i< numCourses; i++) {
            if(map.get(i).inBoundCount == 0) queue.offer(i);
        }
        
        // remove edges based on independent nodes, add new independent nodes in the queue and repeat
        while(!queue.isEmpty()) {
            DirectedGraphNode independentNode = map.get(queue.poll());
            resCourses[res++] = independentNode.label;
            for(Integer adjacencyNodeLabel : independentNode.adjacencyList) {
                map.get(adjacencyNodeLabel).inBoundCount--;
                if(map.get(adjacencyNodeLabel).inBoundCount ==0) queue.offer(adjacencyNodeLabel);
            }
        }
        
        return res == numCourses ? resCourses : new int[0];
    }
}

class DirectedGraphNode {
    int label;
    int inBoundCount;
    List<Integer> adjacencyList;
    
    public DirectedGraphNode(int label) {
        this.inBoundCount = 0;
        this.label = label;
        adjacencyList = new ArrayList<Integer>();
    }
}
