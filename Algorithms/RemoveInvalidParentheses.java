//O(nk), space k

//The program only generates valid answers. Every path in the search generates one valid answer. 
//The whole search space is a tree with k leaves. The number of nodes in the tree is roughly O(k). 
// But this is not always true, for example a degenerated tree.
//To generate one node it requires O(n) time from the string concatenation among other things. So roughly O(nk). 
//Accurately O(nm) where m is the total "number of recursive calls" or "nodes in the search tree". 
//Then you need to relate m to n in the worst case.
//I wouldn't worry too much about the accurate complexity analysis of this problem. 
//It would require more mathematics than an interview cares.

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<String>();
        if(s == null) return res;
        dfsRemove(s, res, 0, 0, new char[]{'(', ')'});
        return res;
    }
    
    private void dfsRemove(String s, List<String> res, int start, int lastRemove, char[] paren) {
        int count = 0;
        for(int i = start; i < s.length(); i++) {
            if(s.charAt(i) == paren[0]) count++;
            if (s.charAt(i) == paren[1]) count--;
            if(count < 0) {
                for(int j = lastRemove; j <= i; j++) {
                    if(s.charAt(j) == paren[1] && (j == lastRemove || s.charAt(j) != s.charAt(j-1))) {
                        dfsRemove(s.substring(0, j) + s.substring(j+1), res, i, j, paren);  // starting from 0, not start index !!!
                    }
                }
                return;
            }
        }
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        String reversed =  sb.toString();
        if(paren[0] == '(') {
            dfsRemove(reversed, res, 0, 0, new char[]{')', '('});
        } else {
            res.add(reversed);
        }
    }
}

// still close to O(n!)
public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<String>();
        
        if(s == null) return res;
        Queue<Tuple> queue = new LinkedList<Tuple>();
        queue.offer(new Tuple(s, 0, ')'));
        if(isValid(s)) {
            res.add(s);
            return res;
        }
        
        while(!queue.isEmpty()) {
            Tuple cur = queue.poll();
            for(int i = cur.start; i < cur.string.length(); i++) {
                if(cur.string.charAt(i) != '(' && cur.string.charAt(i) != ')') continue;
                if(i > cur.start && cur.string.charAt(i-1) == cur.string.charAt(i)) continue;
                if(cur.removed == '(' && cur.string.charAt(i) == ')') continue;
                
                String next = cur.string.substring(0, i) + cur.string.substring(i+1);
                if(isValid(next)) {
                    res.add(next);
                } else if(res.isEmpty()) {
                    queue.offer(new Tuple(next, i, cur.string.charAt(i)));
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

class Tuple {
    public final String string;
    public final int start;
    public final char removed;
    
    public Tuple (String string, int start, char removed) {
        this.string = string;
        this.start = start;
        this.removed = removed;
    }
}

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
