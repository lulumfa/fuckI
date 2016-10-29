// space O(n!) runtime O(n!)
//https://discuss.leetcode.com/topic/28827/share-my-java-bfs-solution
public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<String>();
        
        if(s == null) return res;
        
        HashSet<String> visited = new HashSet<String>();
        Queue<String> queue = new LinkedList<String>();
        queue.offer(s);
        
        boolean found = false;
        while(!queue.isEmpty()) {
            String cur = queue.poll();
            if(isValid(cur)) {
                res.add(cur);
                found = true;
            }
            
            if(found) continue;
            
            for(int i = 0; i < cur.length(); i++) {
                if(cur.charAt(i) == '(' || cur.charAt(i) == ')') {
                    String next = cur.substring(0, i) + cur.substring(i+1, cur.length());
                    if(!visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);                       
                    }

                }
            }
        }
        
        return res;
    }
    
    private boolean isValid(String s) {
        if(s == null) return false;
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') count++;
            else if(s.charAt(i) == ')' && count-- == 0) return false;
        }
        return count == 0;
    }
}
