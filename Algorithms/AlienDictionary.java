// n * L + (E==n + V == 26)

O(nl) and if n >> L, O(n) space |E| + |V|

public class Solution {
    public String alienOrder(String[] words) {
        if(words == null || words.length ==0) return "";
        HashMap<Character, DirectedGraphNode> map = new HashMap<Character, DirectedGraphNode>();
        
        // construct the graph nodes
        for(String word: words) {
            for(int i = 0; i< word.length(); i++) {
                char c = word.charAt(i);
                if(!map.containsKey(c)) map.put(c, new DirectedGraphNode(c));
            }
        }
        
        //find and add all the edges
        for(int i = 0; i< words.length-1; i++) {
            String cur = words[i];
            String next = words[i+1];
            // next one will always diff first before running out of character, no need to compare length
            for(int j = 0; j < cur.length(); j++) {
                if(j == next.length()) return ""; // not actually sorted is a valid test case, the code should handle this
                if(cur.charAt(j) != next.charAt(j)) {
                    char curChar = cur.charAt(j);
                    char nextChar = next.charAt(j);
                    map.get(curChar).ajacencyList.add(map.get(nextChar));
                    map.get(nextChar).inBound++;
                    break;
                }
            }
        }
        
        // find the zero inBound nodes and add them to the queue
        Queue<Character> queue = new LinkedList<Character>();
        for(Character c : map.keySet()) {
            if(map.get(c).inBound == 0) queue.offer(c);
        }
        
        // poll from queue and add them to the result sb, remove the edges starting from this node and decrement the corresponding inBounds and check if there is zero inBounds and add them to queue, repeat
        StringBuilder sb = new StringBuilder();
        
        while(!queue.isEmpty()) {
            char independentChar = queue.poll();
            sb.append(independentChar);
            for(DirectedGraphNode ajacencyNode: map.get(independentChar).ajacencyList) {
                ajacencyNode.inBound--;
                if(ajacencyNode.inBound == 0) queue.offer(ajacencyNode.label);
            }
        }
        
        return sb.length() == map.keySet().size() ? sb.toString() : "";
    }
}

class DirectedGraphNode {
    char label;
    int inBound;
    List<DirectedGraphNode> ajacencyList;
    
    public DirectedGraphNode(char label) {
        this.label = label;
        this.inBound = 0;
        this.ajacencyList = new ArrayList<DirectedGraphNode>();
    }
}
