// O(n!) space O(2^n)

public class Solution {
    public boolean canWin(String s) {
        if(s == null) return true;
        return dfsWithMemo(s, new HashMap<String, Boolean>());
    }
    
    private boolean dfsWithMemo(String s, HashMap<String, Boolean> memo) {
        if(memo.containsKey(s)) return memo.get(s);       
        char[] cur = s.toCharArray();
        
        for(int i = 1; i < cur.length; i++) {
            if(cur[i] == cur[i-1] && cur[i] == '+') {
                cur[i] = cur[i-1] = '-';
                String flipped = new String(cur);
                if(!dfsWithMemo(flipped, memo)) {
                    memo.put(s, true);
                    cur[i] = cur[i-1] = '+';
                    return true;
                }
                cur[i] = cur[i-1] = '+';
            }
        }
        memo.put(s, false);
        return false;
    }
}
