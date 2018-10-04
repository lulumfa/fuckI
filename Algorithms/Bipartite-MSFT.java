A B
B C
C D
D A

true

A B
B C
C A
false

Class BiPartite {

    public boolean canBeBiPartite(String[][] input) {
        if (input == null || input.length == 0) return false; // might be true based on consumer
        Map<String, GraphNode> map = new HashMap<String, GraphNode>();
        for (String[] edge : input) {
            String s = edge[0], t = edge[1];
            if (!map.containsKey(s)) {
                map.put(s, new GraphNode(s));
            }
            map.get(s).add(t);
        }
        
        Queue<GraphNode> queue = new LinkedList<GraphNode>();
        Iterator<GraphNode> iter = map.keySet().iterator();
        
        while (iter.hasNext()) {
            GraphNode root = iter.next();
            root.setColor(0);
            
            queue.offer(root)
            while (!queue.isEmpty()) {
                GraphNode cur = queue.poll();
                map.remove(cur.label);
                for (GraphNode neighbor : cur.neighbors) {
                    if (neighbor.color == cur.getColor()) return false;
                    else if (neighbor.getColor() == -1) {
                        neighbor.setColor(1 - cur.getColor());
                        queue.offer(neighbor);
                    }
                }
            }
            iter = map.keySet().iterator();
        }
        
        return true;
    }
}


class GraphNode {
    String label;
    private integer color; // valid value would be 0, or 1
    List<GraphNode> neighbors;
    
    public GraphNode(String label) {
        this.label = label;
        this.neighbors = new ArrayList<GraphNode>();
        this.color = -1;
    }
    
    public void setColor(int color) {
        this.color = color;
    } 
}
